package integrador.seed.camel;

import integrador.seed.business.processors.DeleteArquivoProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class SftpRouter extends RouteBuilder {

    @Value("${camel.component.sftp.host}")
    private String HOST;

    @Value("${camel.component.sftp.username}")
    private String USERNAME;

    @Value("${camel.component.sftp.password}")
    private String PASSWORD;

    @Value("${camel.component.smb.host}")
    private String SMB_HOST;

    @Value("${camel.component.smb.username}")
    private String SMB_USERNAME;

    @Value("${camel.component.smb.password}")
    private String SMB_PASSWORD;

    @Value("${camel.component.smb.share}")
    private String SMB_SHARE;

    @Value("${camel.component.smb.domain}")
    private String SMB_DOMAIN;

    @Override
    public void configure() throws Exception {

        from("sftp://" + HOST + "/caminho/por/seu/file?username=" + USERNAME + "&password=" + PASSWORD)
                .choice()
                    .when(header("CamelFileName").endsWith(".REM"))
                        .to("direct: processFile")
                        .log("File ${header.CamelFileName} is processed.")
                    .otherwise()
                        .log("File ${header.CamelFileName} is skipped.")
                .end()
        ;

        from("direct: processFile")
                .routeId("ProcessFile")
                .routeDescription("Contem regras de negocio do File")
                // Processamento do arquivo
                .to("direct: sendFileNeexera")
                .onCompletion()
                    .onCompleteOnly()
                    .log("deleta")
    //                    .to("direct: moveFileToDone")
                .end()
        ;

        from("smb:" + SMB_HOST + SMB_SHARE +
                "?username=" + URLEncoder.encode(SMB_USERNAME, StandardCharsets.UTF_8.toString()) +
                "&password=" + URLEncoder.encode(SMB_PASSWORD, StandardCharsets.UTF_8.toString()) +
                "&domain=" + SMB_DOMAIN +
//                "&searchPattern=*.txt" +
                "&path=/skyline/pagamento/gnre%20pagamento")
                //processamento
                .log("Processing file: ${file:name}")
                .log("Processing file: ${body}")
        ;

        from("direct: moveFileToDone")
                .routeId("MoveFileToDone")
                .routeDescription("Move os arquivos processados para o path done")
                //processamento
                .to("sftp://" + HOST + "/payment/ITAU/done?username=" + USERNAME + "&password=" + PASSWORD + "&fileName=${header.CamelFileName}")
                .bean(DeleteArquivoProcessor.class)
        ;
    }
}