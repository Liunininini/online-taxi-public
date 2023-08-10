package com.mashibing.apipassenger.controller;

import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.dto.TokenResponse;
import com.example.internalcommon.dto.TokenResult;
import com.mashibing.apipassenger.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/token-refresh")
    public ResponseResult tokenRefresh(@RequestBody TokenResponse tokenResponse){
        String refreshTokenSrc = tokenResponse.getRefreshToken();
        return tokenService.refreshToken(refreshTokenSrc);

    }
}
