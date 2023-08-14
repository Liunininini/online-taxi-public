package com.controller;

import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.request.VerificationCodeDTO;
import com.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/user")
    public ResponseResult loginOrReg(@RequestBody VerificationCodeDTO verificationCodeDTO) {
        String pasengerPhone = verificationCodeDTO.getPassengerPhone();
        return userService.loginOrReg(pasengerPhone);
    }

    @GetMapping("/user/{phone}")
    public ResponseResult getuset(@PathVariable("phone") String phone){
        return userService.getUserByPhone(phone);
    }
}
