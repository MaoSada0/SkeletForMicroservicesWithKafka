package ru.qq.node.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Document;
import ru.qq.node.service.DocumentMessageService;

@Service
@RequiredArgsConstructor
public class DocumentMessageServiceImpl implements DocumentMessageService {

    @Override
    public void processDocumentMessage(Document document) {

    }
}
