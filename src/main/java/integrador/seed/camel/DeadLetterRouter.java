package integrador.seed.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DeadLetterRouter extends RouteBuilder {

    @Value("${integration.max-redeliveries}")
    private Integer maxRedeliveries;

    @Value("${integration.queues.sandbox.queue}")
    private String queueName;

    @Value("${integration.exchange}")
    private String exchange;

    @Value("${integration.concurrent-consumers}")
    private Integer concurrentConsumers;

    @Value("${integration.queues.sandbox.routingkey}")
    private String routingkey;

    @Value("${integration.queues.sandbox.queue}")
    private String queue;

    @Value("${integration.queues.sandbox.deadletter}")
    private String deadletter;

    @Value("${integration.queues.sandbox.parkinglot}")
    private String parkinglot;

    @Override
    public void configure() {
//
//        from("spring-rabbitmq:" + exchange +
//                "?queues=" + queue +
//                "&routingKey=" + routingkey +
//                "&exchangePattern=InOnly" +
//                "&prefetchCount=1" +
//                "&concurrentConsumers=" + concurrentConsumers +
//                "&prefetchCount=200" +
//                "&arg.queue.durable=true" +
//                "&exchangeType=x-delayed-message" +
//                //"&deadLetterExchange=" +//Nome do exchange para o qual as mensagens não entregues devem ser enviadas.(NÃO TEM NOME DEFINIDO)
//                "&deadLetterRoutingKey=" + deadletter)
//                .to("direct:dlqProcessor")
//        ;
//
//
//        //Enviando para a DLQ
//        from("direct:sendToDlq")
//                .routeId("SendToDlqRoute")
//                .log("Enviando para DLQ >>> ${body}")
//                .to("spring-rabbitmq:" + queueName + ".deadletter" +
//                        "?queues=" + queueName + ".deadletter" +
//                        "&exchangePattern=InOnly" +
//                        "&exchangeType=direct" +
//                        "&deadLetterExchange=" + queueName + ".parkingLot" +
//                        "&deadLetterQueue=" + queueName + ".parkingLot" +
//                        "&deadLetterRoutingKey=" + queueName + ".deadletter")
//        ;
//
//
//        //Consumindo da DLQ
//        from("spring-rabbitmq:" + queueName + ".deadletter" +
//                "?queues=" + queueName + ".deadletter" +
//                "&exchangePattern=InOnly" +
//                "&exchangeType=direct" +
//                "&prefetchCount=200" +
//                "&deadLetterExchange=" + queueName + ".parkingLot" +
//                "&deadLetterQueue=" + queueName + ".parkingLot" +
//                "&deadLetterRoutingKey=" + queueName + ".deadletter")
//                .routeId("DlqConsumer")
//                .to("direct:dlqProcessor")
//        ;
//
//        from("direct:dlqProcessor")
//                .routeId("DlqProcessor")
//                .bean(DeadLetterProcessor.class)
////                .log("Tentativa ${property.CamelRedeliveryCounter} >>> Body >>> ${body}")
//                .doTry()
//                    .setProperty("originalBody", body())
//                    .to("direct:process")
//                .doCatch(Exception.class)
//                    .bean(DeadLetterProcessor.class, "doCatch")
//                    .choice()
//                    .when(header(Exchange.REDELIVERY_COUNTER).isEqualTo(maxRedeliveries))
//                    .log("Registro ${body} enviado para Parking Lot")
//                    .bean(DeadLetterProcessor.class, "sendToParkingLot")
//                .otherwise()
//                    .setBody(exchangeProperty("originalBody"))
//                    .to("direct:sendToDlq")
//                .end()
//        ;
    }
}
