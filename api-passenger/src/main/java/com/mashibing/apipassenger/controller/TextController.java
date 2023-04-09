package com.mashibing.apipassenger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextController {

    @GetMapping("/test")
    public String text(){

        return "OK";
    }
}
