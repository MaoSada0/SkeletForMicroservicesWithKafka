package ru.qq.node.service.kafka_listeners;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.telegram.telegrambots.meta.api.objects.Document;

public interface KafkaListener {
    void consumeTextMessage(String text);
    void consumeDocumentMessage(Document document);
}
