package integrador.seed.business.processors;

import integrador.seed.core.AbstractDeadLetterProcessor;
import org.apache.camel.Exchange;

public class DeadLetterProcessor extends AbstractDeadLetterProcessor {

    @Override
    public void process(Exchange exchange) {
        super.process(exchange);
        // Processamentos customizados devem ser feitos abaixo dessa linha
    }

    @Override
    public void doCatch(Exchange exchange) {
        super.doCatch(exchange);
        // Processamentos customizados devem ser feitos abaixo dessa linha
    }

    @Override
    public void sendToParkingLot(Exchange exchange) {
        super.sendToParkingLot(exchange);
        // Processamentos customizados devem ser feitos abaixo dessa linha
    }
}
