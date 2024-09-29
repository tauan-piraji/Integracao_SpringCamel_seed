package integrador.seed.business.processors;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Value;

public class DeleteArquivoProcessor implements Processor {

    @Value("${camel.component.sftp.host}")
    private String HOST;

    @Value("${camel.component.sftp.username}")
    private String USERNAME;

    @Value("${camel.component.sftp.password}")
    private String PASSWORD;

    @Override
    public void process(Exchange exchange) throws Exception {
        String originalFilePath = exchange.getIn().getHeader("CamelFileAbsolutePath", String.class);
        deleteRemoteFile(originalFilePath);
    }

    private void deleteRemoteFile(String filePath) throws Exception {
        JSch jsch = new JSch();
        Session session = null;
        ChannelSftp channelSftp = null;

        try {
            session = jsch.getSession(USERNAME, HOST, 22);
            session.setPassword(PASSWORD);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            channelSftp.rm(filePath);
        } finally {
            if (channelSftp != null) {
                channelSftp.disconnect();
            }
            if (session != null) {
                session.disconnect();
            }
        }
    }
}
