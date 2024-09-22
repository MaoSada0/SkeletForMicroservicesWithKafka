package ru.qq.dispatcher.service;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface MainService {
    boolean checkIsActive(Long userId);
    void processUpdate(Update update);
}
