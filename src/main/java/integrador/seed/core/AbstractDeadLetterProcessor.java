package integrador.seed.core;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/*
    Processor default para DeadLetterQueues dos projetos de integração La Moda
    Seta manualmente o redelivery counter para controle de redeliveries caso seja necessário o uso de parking lots ou
    tentativas finitas de re-enfileiramento
 */
public abstract class AbstractDeadLetterProcessor implements Processor {

    @Override
    public void process(Exchange exchange) {
        Integer redeliveryCounter = (Integer) exchange.getIn().getHeader(Exchange.REDELIVERY_COUNTER, 0);
        redeliveryCounter++;
        exchange.setProperty(Exchange.REDELIVERY_COUNTER, redeliveryCounter);
    }

    public void doCatch(Exchange exchange) {
        Integer count = exchange.getProperty(Exchange.REDELIVERY_COUNTER, Integer.class);
        exchange.getIn().setHeader(Exchange.REDELIVERY_COUNTER, count);
        exchange.getIn().setHeader(Exchange.REDELIVERED, true);
    }

    public void sendToParkingLot(Exchange exchange) {
        exchange.getIn().getExchange().setException(new Exception("Registro enviado para Parking Lot"));
    }
}
