package ru.qq.dispatcher.kafka_listener.impl;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import ru.qq.dispatcher.kafka_listener.KafkaListener;

@Service
@Log4j
public class KafkaListenerImpl implements KafkaListener {


    @Override
    @org.springframework.kafka.annotation.KafkaListener(
            topics = "${spring.kafka.topic-message-to-dispatcher}",
            groupId = "${spring.kafka.group-id.to-dispatcher}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consumeTextMessage(String text){
        log.debug("Received text message from kafka: " + text);
    }
}
