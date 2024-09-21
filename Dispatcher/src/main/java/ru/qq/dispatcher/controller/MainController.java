package ru.qq.dispatcher.controller;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.qq.dispatcher.service.MainService;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @PostMapping("test-kafka")
    public ResponseEntity<String> getStats(@RequestParam(value = "message", required = true) String message){
        mainService.sendMessage(message);

        return new ResponseEntity<>("Message send! ", HttpStatus.OK);
    }
}
