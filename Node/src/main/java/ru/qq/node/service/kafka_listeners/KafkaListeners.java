package ru.qq.node.service.kafka_listeners;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface KafkaListeners {
    void consumeMessage(ConsumerRecord<String, String> record);
}
