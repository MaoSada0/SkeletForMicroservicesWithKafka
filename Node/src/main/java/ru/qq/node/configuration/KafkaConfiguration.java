package ru.qq.node.configuration;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.telegram.telegrambots.meta.api.objects.Document;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.group-id.string}")
    private String GROUP_ID_STRING;

    @Value("${spring.kafka.group-id.document}")
    private String GROUP_ID_DOCUMENT;

    @Bean
    public ConsumerFactory<String, String> consumerStringFactory() {
        Map<String, Object> configProperties = new HashMap<>();
        configProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProperties.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID_STRING);

        return new DefaultKafkaConsumerFactory<>(configProperties);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerStringFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerStringFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Document> consumerDocumentFactory() {
        Map<String, Object> configProperties = new HashMap<>();
        configProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configProperties.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID_DOCUMENT);
        configProperties.put(JsonDeserializer.TRUSTED_PACKAGES, "org.telegram.telegrambots.meta.api.objects");

        return new DefaultKafkaConsumerFactory<>(configProperties);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Document> kafkaListenerContainerDocumentFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Document> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerDocumentFactory());
        return factory;
    }
}
