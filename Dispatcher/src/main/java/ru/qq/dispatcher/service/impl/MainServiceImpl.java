package ru.qq.dispatcher.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.qq.dispatcher.client.NodeRestClient;
import ru.qq.dispatcher.service.MainService;

@Service
@RequiredArgsConstructor
@Log4j
public class MainServiceImpl implements MainService {

    @Value("${spring.kafka.topic-message}")
    private String TOPIC_MESSAGE;

    @Value("${spring.kafka.topic-file}")
    private String TOPIC_FILE;

    private final KafkaTemplate<String, String> kafkaStringTemplate;
    private final KafkaTemplate<String, Document> kafkaDocumentTemplate;

    private final NodeRestClient nodeRestClient;

    @Override
    public boolean checkIsActive(Long userId) {
        return nodeRestClient.checkIsActive(userId);
    }

    @Override
    public void processUpdate(Update update) {
        Message message = update.getMessage();

        log.debug(message.hasDocument());

        if(message.hasDocument())
            kafkaDocumentTemplate.send(TOPIC_FILE, message.getDocument());
        else
            kafkaStringTemplate.send(TOPIC_MESSAGE, message.getText());
    }
}
