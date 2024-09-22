package ru.qq.node.service;

import org.telegram.telegrambots.meta.api.objects.Document;

public interface DocumentMessageService {
    void processDocumentMessage(Document document);
}
