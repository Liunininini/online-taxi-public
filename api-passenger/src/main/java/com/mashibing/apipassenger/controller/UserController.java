package com.mashibing.apipassenger.controller;

import com.example.internalcommon.dto.ResponseResult;
import com.mashibing.apipassenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户信息
     * @param request
     * @return
     */
    @GetMapping("/usets")
    public ResponseResult getUset(HttpServletRequest request){

        //从http请求中获取accessToken
        String accessToken = request.getHeader("Authorization");
        //根据accessToken去查询

        return userService.getUserByAccessToken(accessToken);

    }
}
