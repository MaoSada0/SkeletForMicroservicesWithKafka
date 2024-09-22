package ru.qq.dispatcher.configuration;

import org.apache.kafka.clients.admin.NewTopic;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Message;


import java.util.HashMap;
import java.util.Map;


@Configuration
public class KafkaProducerConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String BOOSTRAP_SERVERS;

    @Value("${spring.kafka.topic-message}")
    private String TOPIC_MESSAGE;

    @Value("${spring.kafka.topic-file}")
    private String TOPIC_FILE;

    @Value("${spring.kafka.group-id.from-dispatcher}")
    private String GROUP_ID;

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
    public Map<String, Object> producerConfiguration() {
        Map<String, Object> configProperties = new HashMap<>();
        configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOSTRAP_SERVERS);
        configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configProperties.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);


        return configProperties;
    }

    @Bean
    public ProducerFactory<String, Message> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfiguration());
    }

    @Bean
    public KafkaTemplate<String, Message> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }


}
