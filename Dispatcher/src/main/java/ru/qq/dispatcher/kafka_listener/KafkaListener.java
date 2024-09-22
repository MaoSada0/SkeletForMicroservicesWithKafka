package ru.qq.dispatcher.kafka_listener;

public interface KafkaListener {
    void consumeTextMessage(String text);
}
