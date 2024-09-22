package ru.qq.dispatcher.component.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.qq.dispatcher.component.TelegramBot;
import ru.qq.dispatcher.component.UpdateProcessor;
import ru.qq.dispatcher.service.MainService;

@Component
@RequiredArgsConstructor
@Log4j
public class UpdateProcessorImpl implements UpdateProcessor {

    private final MainService mainService;
    private final TelegramBot telegramBot;


    @Override
    public void processUpdate(Update update) {
        if(!update.hasMessage()) return;

        Message message = update.getMessage();
        Long userId = message.getFrom().getId();
        boolean active = mainService.checkIsActive(userId);

        if(!active) {
            SendMessage sendMessage = SendMessage.builder()
                    .chatId(String.valueOf(message.getChatId()))
                    .text("Вы не зарегестрированы!")
                    .build();

            telegramBot.sendAnswerMessage(sendMessage);
            return;
        }

    }


}
