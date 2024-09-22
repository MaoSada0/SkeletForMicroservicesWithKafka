package ru.qq.dispatcher.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.qq.dispatcher.component.UpdateProcessor;

@RestController
@RequiredArgsConstructor
@RequestMapping("callback/telegram/update")
@Log4j
public class TelegramWebhookController {


    private final UpdateProcessor updateProcessor;

    @PostMapping
    public ResponseEntity<String> onUpdateReceived(@RequestBody Update update) {
        log.debug(update.getMessage());

        updateProcessor.processUpdate(update);

        return ResponseEntity.ok("Webhook received");
    }
}
