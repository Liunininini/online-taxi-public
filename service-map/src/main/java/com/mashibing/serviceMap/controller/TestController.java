package com.mashibing.serviceMap.controller;

import com.example.internalcommon.dto.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/gogo")
    public ResponseResult gogo() {
        return ResponseResult.success("ok");
    }
}
