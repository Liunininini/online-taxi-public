package com.service;

import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.request.VerificationCodeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Slf4j
public class UserService {

    public ResponseResult loginOrReg(String passengerPhone){
        //根据手机号查询用户信息

        //判断用户是否存在

        //如果不存在,插入用户信息

        return ResponseResult.success();

    }

}
