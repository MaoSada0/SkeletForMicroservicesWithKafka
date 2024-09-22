package ru.qq.dispatcher.component.impl;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.qq.dispatcher.component.TelegramBot;


@Component
@Log4j
public class TelegramBotImpl extends TelegramWebhookBot implements TelegramBot {
    @Value("${telegram-bot.username}")
    private String BOT_USERNAME;

    @Value("${telegram-bot.token}")
    private String BOT_TOKEN;

    @Value("${telegram-bot.webhook-uri}")
    private String BOT_URI;

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return null;
    }

    @Override
    public String getBotPath() {
        return "telegram/update";
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init(){

        try {
            var setWebhook = SetWebhook.builder()
                    .url(BOT_URI)
                    .build();
            this.setWebhook(setWebhook);
        } catch (TelegramApiException e) {
            log.error(e);
        }
    }

    @Override
    public void sendAnswerMessage(SendMessage message){
        if(message != null){
            try {
                execute(message);
            } catch (TelegramApiException e) {
                log.error(e);
            }
        } else {
            log.error("Received message is null");
        }
    }
}
