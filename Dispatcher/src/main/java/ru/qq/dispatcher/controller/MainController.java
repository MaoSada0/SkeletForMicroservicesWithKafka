package ru.qq.dispatcher.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class MainController {


    @GetMapping()
    public ResponseEntity<String> getStats(@RequestBody String json, @RequestParam("nickname") String nickname){

        return new ResponseEntity<>("DSAD", HttpStatus.OK);
    }
}
