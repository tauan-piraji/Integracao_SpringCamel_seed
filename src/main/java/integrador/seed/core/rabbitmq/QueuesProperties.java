package integrador.seed.core.rabbitmq;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "integration")
public class QueuesProperties {

    private Map<String, QueueProperty> queues;
    private String exchange;
}

@Getter
@Setter
class QueueProperty {

    private String routingkey;
    private String queue;
    private String deadletter;
    private String parkinglot;
    private Integer ttl;
}