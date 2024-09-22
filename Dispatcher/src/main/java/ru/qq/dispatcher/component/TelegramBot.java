package ru.qq.dispatcher.component;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface TelegramBot {
    void sendAnswerMessage(SendMessage message);
}
