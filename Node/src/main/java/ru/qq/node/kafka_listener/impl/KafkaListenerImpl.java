package ru.qq.node.kafka_listener.impl;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.qq.node.service.TextMessageService;
import ru.qq.node.kafka_listener.KafkaListener;

@Service
@Log4j
@AllArgsConstructor
public class KafkaListenerImpl implements KafkaListener {

    private final TextMessageService textMessageService;

    @Override
    @org.springframework.kafka.annotation.KafkaListener(
            topics = "${spring.kafka.topic-message}",
            groupId = "${spring.kafka.group-id.from-dispatcher}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consumeTextMessage(Message textMessage){

        log.debug(textMessage);
        textMessageService.processTextMessage(textMessage);
    }

    @Override
    @org.springframework.kafka.annotation.KafkaListener(
            topics = "${spring.kafka.topic-file}",
            groupId = "${spring.kafka.group-id.from-dispatcher}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consumeDocumentMessage(Message docMessage) {
        log.debug("Received file message from kafka: " + docMessage.getDocument().getFileName());
    }
}
