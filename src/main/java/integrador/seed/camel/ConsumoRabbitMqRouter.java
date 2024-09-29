package integrador.seed.camel;

import integrador.seed.business.services.TablePatternOracleService;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConsumoRabbitMqRouter extends RouteBuilder {

    @Value("${integration.exchange}")
    private String exchange;

    @Value("${integration.queues.sandbox.routingkey}")
    private String routingkey;

    @Value("${integration.queues.sandbox.queue}")
    private String queue;

    @Value("${integration.concurrent-consumers}")
    private Integer concurrentConsumers;

    @Override
    public void configure() {

        from("spring-rabbitmq:" + exchange +
                "?queues=" + queue +
                "&routingKey=" + routingkey +
                "&exchangePattern=InOnly" +
                "&prefetchCount=1" +//trocar para 50
                "&concurrentConsumers=" + concurrentConsumers +
                "&arg.queue.durable=true" +
                "&exchangeType=x-delayed-message")
                .routeId("QueueConsumer")
                //O processamento do bean deve estar dentro do dotry para garantir que toda Exception seja enviada para a DeadLetterQueue
                .doTry()
                    .removeHeaders("*")
                    .setProperty("originalBody", body())
                    .to("direct: processMessage")
                .doCatch(Exception.class)
                    //Enviar para DLQ manualmente
                    .setBody(simple("${originalBody}"))
    //                    .to("direct:sendToDlq")
                .end()
        ;

        from("direct: processMessage")
                .routeId("ProcessMessage")
                .routeDescription("Faz processamento dos dados, atualiza data de controle e envia od items para a vertem")
                //processamento
                .bean(TablePatternOracleService.class, "getAll")
                .setProperty("exemploOracle", body())
                .split(body())
                    .log("${body}")
                .end()
        ;

    }
}
