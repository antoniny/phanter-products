package br.com.awm.pantherproducts.kafka.config;

import br.com.awm.pantherproducts.kafka.config.lib.CompletableFutureReplyingKafkaOperations;
import br.com.awm.pantherproducts.kafka.config.lib.CompletableFutureReplyingKafkaTemplate;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;

import java.time.Duration;

@Configuration
public class TopicsKafkaConfig {

    @Value("${spring.kafka.topic.request-api-request-product}")
    private String requestApiRequestProduct;

    @Value("${spring.kafka.topic.reply-api-request-product}")
    private String replyApiRequestProduct;

    @Value("${spring.kafka.topic.create-product}")
    private String createProduct;

    @Value("${spring.kafka.topic.create-api-request-product}")
    private String createApiRequestProduct;

    @Value("${spring.kafka.binder.replication-factor}")
    private String replicationFactor;

    @Value("${spring.kafka.topic.partition-default}")
    private int defaultPartitions;

    @Value("${spring.kafka.reply.timeout}")
    private String replyTimeout;

    @Autowired
    private BaseKafkaConfig baseKafkaConfig;

    @Bean
    public CompletableFutureReplyingKafkaOperations<String, String, String> replyKafkaTemplate() {
        CompletableFutureReplyingKafkaTemplate<String, String, String> template =
                new CompletableFutureReplyingKafkaTemplate<>(baseKafkaConfig.producerFactory(),
                        replyContainer());
        template.setDefaultReplyTimeout(Duration.ofMillis(Long.parseLong(replyTimeout)));
        return template;
    }

    // Listener Container to be set up in ReplyingKafkaTemplate
    @Bean
    public KafkaMessageListenerContainer<String, String> replyContainer() {
        ContainerProperties containerProperties = new ContainerProperties(replyApiRequestProduct);

        return new KafkaMessageListenerContainer<>(baseKafkaConfig.consumerFactory(), containerProperties);
    }

    // Automatic Topic creation
    @Bean
    public NewTopic topicRequestApiRequestProduct() {
        return new NewTopic(requestApiRequestProduct, defaultPartitions, Short.parseShort(replicationFactor));
    }

    @Bean
    public NewTopic topicReplyApiRequestProduct() {
        return new NewTopic(replyApiRequestProduct, defaultPartitions, Short.parseShort(replicationFactor));
    }

    @Bean
    public NewTopic topicCreateProduct() {
        return new NewTopic(createProduct, defaultPartitions, Short.parseShort(replicationFactor));
    }

    @Bean
    public NewTopic topicCreateApiRequestProduct() {
        return new NewTopic(createApiRequestProduct, defaultPartitions, Short.parseShort(replicationFactor));
    }

}
