package ru.qq.node.service.kafka_listeners.impl;

import lombok.extern.log4j.Log4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Document;
import ru.qq.node.service.kafka_listeners.KafkaListener;

@Service
@Log4j
public class KafkaListenerImpl implements KafkaListener {


    @Override
    @org.springframework.kafka.annotation.KafkaListener(
            topics = "${spring.kafka.topic-message}",
            groupId = "${spring.kafka.group-id.string}",
            containerFactory = "kafkaListenerContainerStringFactory"
    )
    public void consumeTextMessage(String text){
        log.debug("Received text message from kafka: " + text);
    }

    @Override
    @org.springframework.kafka.annotation.KafkaListener(
            topics = "${spring.kafka.topic-file}",
            groupId = "${spring.kafka.group-id.document}",
            containerFactory = "kafkaListenerContainerDocumentFactory"
    )
    public void consumeDocumentMessage(Document document) {
        log.debug("Received file message from kafka: " + document.getFileName());
    }
}
