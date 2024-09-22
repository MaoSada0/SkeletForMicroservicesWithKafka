package ru.qq.node.service;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface TextMessageService {
    void processTextMessage(Message textMessage);
}
