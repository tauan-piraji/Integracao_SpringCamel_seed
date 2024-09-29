package integrador.seed.camel;

import integrador.seed.business.services.TablePatternMySqlService;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CamelRouter extends RouteBuilder {

    @Value("${integration.exchange}")
    private String exchange;

    @Value("${integration.queues.sandbox.routingkey}")
    private String routingkey;

    @Value("${integration.queues.sandbox.queue}")
    private String queue;

    @Override
    public void configure() {

        from("timer://foo?repeatCount=1")
                .routeId("Start")
                .routeDescription("Start")
                .log("Iniciando integrador...")
                .to("direct: processEnqueue")
        ;

//        from("timer://foo?fixedRate=true&period=1800000") em ms
//                .routeId("Execucao")
//                .routeDescription("Execução schedulada")
//                .log("Scheduller 30 em 30 min...")
//                .to("direct: processEnqueue")
//        ;

        from("direct: processEnqueue")
                .to("direct: findTablePattern")
                .to("direct: sendMessageToRabbitMq")
        ;

        from("direct: findTablePattern")
                .routeId("FindTablePattern")
                .routeDescription("Busca todos os dados da tabela Db_table_pattern")
                //processamento
                .bean(TablePatternMySqlService.class, "getAll")
                .end()
        ;

        from("direct: sendMessageToRabbitMq")
                .routeId("SendMessageToRabbitMq")
                .routeDescription("Envia os dados para fila")
                //processamento
                .marshal("jacksonDataFormat")
                .log("${body}")
                .to("spring-rabbitmq:" + exchange +
                        "?queues=" + queue +
                        "&exchangePattern=InOnly" +
                        "&exchangeType=direct" +
                        "&routingKey=" + routingkey)
        ;
    }
}
