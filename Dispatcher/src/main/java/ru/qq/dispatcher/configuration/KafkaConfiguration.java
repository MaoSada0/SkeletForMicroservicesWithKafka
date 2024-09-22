package ru.qq.dispatcher.configuration;

import org.apache.kafka.clients.admin.NewTopic;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.telegram.telegrambots.meta.api.objects.Document;


import java.util.HashMap;
import java.util.Map;


@Configuration
public class KafkaConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String BOOSTRAP_SERVERS;

    @Value("${spring.kafka.topic-message}")
    private String TOPIC_MESSAGE;

    @Value("${spring.kafka.topic-file}")
    private String TOPIC_FILE;

    @Bean
    public NewTopic messageTopic(){
        return TopicBuilder
                .name(TOPIC_MESSAGE)
                .build();
    }

    @Bean
    public NewTopic fileTopic(){
        return TopicBuilder
                .name(TOPIC_FILE)
                .build();
    }

    @Bean
    public Map<String, Object> producerStringConfiguration() {
        Map<String, Object> configProperties = new HashMap<>();
        configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOSTRAP_SERVERS);
        configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return configProperties;
    }

    @Bean
    public ProducerFactory<String, String> producerStringFactory() {
        return new DefaultKafkaProducerFactory<>(producerStringConfiguration());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaStringTemplate() {
        return new KafkaTemplate<>(producerStringFactory());
    }

    @Bean
    public Map<String, Object> producerDocumentConfiguration() {
        Map<String, Object> configProperties = new HashMap<>();
        configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOSTRAP_SERVERS);
        configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return configProperties;
    }

    @Bean
    public ProducerFactory<String, Document> producerDocumentFactory() {
        return new DefaultKafkaProducerFactory<>(producerDocumentConfiguration());
    }

    @Bean
    public KafkaTemplate<String, Document> kafkaDocumentTemplate() {
        return new KafkaTemplate<>(producerDocumentFactory());
    }


}
