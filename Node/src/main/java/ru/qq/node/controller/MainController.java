package ru.qq.node.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("node/api")
@RequiredArgsConstructor
@Log4j
public class MainController {

    @GetMapping("user/activation")
    public ResponseEntity<Boolean> isActiveUser(@RequestParam(value = "userid") Integer id){
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
