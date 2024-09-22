package ru.qq.dispatcher.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.qq.dispatcher.client.NodeRestClient;
import ru.qq.dispatcher.service.MainService;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {

    @Value("${spring.kafka.topic-name}")
    private String TOPIC_NAME;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final NodeRestClient nodeRestClient;

    @Override
    public boolean checkIsActive(Long userId) {
        return nodeRestClient.checkIsActive(userId);
    }

    @Override
    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC_NAME, message);
    }
}
