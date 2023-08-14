package com.mashibing.apipassenger.controller;

import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.request.VerificationCodeDTO;
import com.mashibing.apipassenger.service.VerificationCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class VerificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    /**
     * 校验验证码
     * @param verificationCodeDTO
     * @return
     */
    @GetMapping("/verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO) {

        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("接收到的手机号" + passengerPhone);
        return verificationCodeService.generatorCOde(passengerPhone);

    }

    /**
     * 双Token刷新
     * @param verificationCodeDTO
     * @return
     */
    @PostMapping("/verification-code-check")
    public ResponseResult verificationCodeCheck(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        String verificationCode = verificationCodeDTO.getVerificationCode();
        log.info("手机号:{},验证码:{}",passengerPhone,verificationCode);
        return verificationCodeService.verificationCodeCheck(passengerPhone,verificationCode);
    }
}
