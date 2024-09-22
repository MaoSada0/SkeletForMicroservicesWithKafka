package ru.qq.node.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.qq.node.service.TextMessageService;

@Service
@RequiredArgsConstructor
public class TextMessageServiceImpl implements TextMessageService {

    private final KafkaTemplate<String, String> kafkaStringTemplate;

    @Value("${spring.kafka.topic-message-to-dispatcher}")
    private String TOPIC_MESSAGE;

    @Override
    public void processTextMessage(Message textMessage) {
        kafkaStringTemplate.send(TOPIC_MESSAGE, "from kafka: " + textMessage.getText());
    }
}
