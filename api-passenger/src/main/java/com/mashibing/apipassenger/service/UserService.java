package com.mashibing.apipassenger.service;

import com.example.internalcommon.constant.CommonStatusEnum;
import com.example.internalcommon.dto.PassengerUser;
import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.dto.TokenResult;
import com.example.internalcommon.request.VerificationCodeDTO;
import com.example.internalcommon.util.JwtUtils;
import com.mashibing.apipassenger.remote.ServicePassengerUserClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class UserService {

    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;

    public ResponseResult getUserByAccessToken(String accessToken) {
        //解析token, 拿到手机号
        TokenResult result = JwtUtils.checkToken(accessToken);
        if (Objects.isNull(result)) {
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(), CommonStatusEnum.TOKEN_ERROR.getValue());
        }
        String phone = result.getPhone();
        log.info("phone:" + phone);
        //根据手机号获取用户信息
        ResponseResult<PassengerUser> userByPhone = servicePassengerUserClient.getUserByPhone(phone);
        PassengerUser passengerUser = userByPhone.getData();
        return ResponseResult.success(passengerUser);
    }
}
