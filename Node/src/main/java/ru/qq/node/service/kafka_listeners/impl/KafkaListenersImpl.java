package ru.qq.node.service.kafka_listeners.impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.qq.node.service.kafka_listeners.KafkaListeners;

@Service
public class KafkaListenersImpl implements KafkaListeners {

    @KafkaListener(topics = "main-topic", groupId = "main-group")
    public void consumeMessage(ConsumerRecord<String, String> record){
        System.out.println("Получено сообщение: " + record.value());
    }
}
