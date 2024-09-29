package integrador.seed.core.rabbitmq;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.GenericWebApplicationContext;

import java.util.Map;

@Configuration
@EnableConfigurationProperties(QueuesProperties.class)
public class AmqpDefaultConfig {

    @Autowired
    private ConnectionFactory connectionFactory;
    @Autowired
    private GenericWebApplicationContext context;

    private final QueuesProperties properties;

    @Autowired
    public AmqpDefaultConfig(QueuesProperties properties) {
        this.properties = properties;
    }

    @PostConstruct
    public void generateBeansRabbitMQ(){

        String exchange = properties.getExchange();

        for (Map.Entry<String, QueueProperty> queuePropertyEntry : properties.getQueues().entrySet()){
            String queueKey = queuePropertyEntry.getKey();
            QueueProperty queueValue = queuePropertyEntry.getValue();

            var admin = new RabbitAdmin(connectionFactory);
            admin.declareExchange(directExchange(exchange));
            admin.declareQueue(queue(queueValue.getQueue(), queueValue.getDeadletter()));
            admin.declareBinding(binding(queueValue.getQueue(), queueValue.getDeadletter(), exchange, queueValue.getRoutingkey()));
            admin.declareQueue(dlq(queueValue.getDeadletter(), queueValue.getQueue(), queueValue.getTtl()));
            admin.declareQueue(parkingLot(queueValue.getParkinglot()));

            context.registerBean("admin-default-" + queueKey, RabbitAdmin.class, admin);
        }
    }

    protected DirectExchange directExchange(String exchange) {
        return ExchangeBuilder
                .directExchange(exchange)
                //.delayed()
                .durable(true)
                .build();
    }

    protected Queue queue(String queue, String dlq) {
        return QueueBuilder.durable(queue)
                .deadLetterExchange("")
                .deadLetterRoutingKey(dlq)
                .build();
    }

    protected Queue parkingLot(String parkinglot) {
        return QueueBuilder.durable(parkinglot)
                .lazy()
                .build();
    }

    protected Queue dlq(String dlq, String queue, Integer ttl) {
        return QueueBuilder.durable(dlq)
                .deadLetterExchange("")
                .deadLetterRoutingKey(queue)
                .ttl(ttl)
                .build();
    }

    protected Binding binding(String queue, String dlq, String exchange, String routingkey) {
        return BindingBuilder.bind(queue(queue, dlq))
                .to(directExchange(exchange))
                .with(routingkey);
    }

}