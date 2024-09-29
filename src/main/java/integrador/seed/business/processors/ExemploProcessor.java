package integrador.seed.business.processors;

import integrador.seed.models.entitiesOracle.DbTablePatternOracle;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ExemploProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String jsonString = exchange.getIn().getBody(String.class);
        DbTablePatternOracle jsonPropertie = exchange.getProperty("exemploOracle", DbTablePatternOracle.class);

        exchange.getIn().setBody(jsonString);
    }
}