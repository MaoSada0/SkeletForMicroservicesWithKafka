package ru.qq.dispatcher.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.qq.dispatcher.client.NodeRestClient;
import ru.qq.dispatcher.component.TelegramBot;
import ru.qq.dispatcher.service.UpdateProcessor;

@Service
@RequiredArgsConstructor
@Log4j
public class UpdateProcessorImpl implements UpdateProcessor {

    private final TelegramBot telegramBot;

    @Value("${spring.kafka.topic-message}")
    private String TOPIC_MESSAGE;

    @Value("${spring.kafka.topic-file}")
    private String TOPIC_FILE;

    private final KafkaTemplate<String, Message> kafkaTemplate;


    private final NodeRestClient nodeRestClient;

    @Override
    public void processUpdate(Update update) {
        if(!update.hasMessage()) return;

        Message message = update.getMessage();
        Long userId = message.getFrom().getId();


        if(!checkIsActive(userId)) {
            sendMessage("Вы не зарегестрировались!", message.getChatId());
            return;
        }

        if(message.hasDocument() && !"application/json".equals(message.getDocument().getMimeType())){
            sendMessage("Тип поддерживаемых файлов только json", message.getChatId());
            return;
        }

        if(message.hasDocument())
            kafkaTemplate.send(TOPIC_FILE, message);
        else
            kafkaTemplate.send(TOPIC_MESSAGE, message);

    }

    private boolean checkIsActive(Long userId) {
        return nodeRestClient.checkIsActive(userId);
    }

    private void sendMessage(String text, Long id){
        SendMessage sendMessage = SendMessage.builder()
                .chatId(String.valueOf(id))
                .text(text)
                .build();

        telegramBot.sendAnswerMessage(sendMessage);
    }


}
