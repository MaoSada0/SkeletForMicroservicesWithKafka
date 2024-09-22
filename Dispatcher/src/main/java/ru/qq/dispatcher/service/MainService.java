package ru.qq.dispatcher.service;

public interface MainService {
    boolean checkIsActive(Long userId);
    void sendMessage(String message);
}
