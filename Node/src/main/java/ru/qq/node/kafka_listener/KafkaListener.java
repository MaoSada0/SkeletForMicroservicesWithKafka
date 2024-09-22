package ru.qq.node.kafka_listener;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface KafkaListener {
    void consumeTextMessage(Message textMessage);
    void consumeDocumentMessage(Message docMessage);
}
