package com.mashibing.apipassenger.service;

import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.responese.NumberCodeResponse;
import com.mashibing.apipassenger.remote.ServiceVefificationcodeClient;
import lombok.val;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class VerificationCodeService {

    @Resource
    private ServiceVefificationcodeClient serviceVefificationcodeClient;

    public String generatorCOde(String passengerPhone){
        //调用验证码服务.获取验证码
        System.out.println("调用验证码服务.获取验证码");

        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVefificationcodeClient.getNumberCode(6);
        int numcode = numberCodeResponse.getData().getNumberCode();
        System.out.println("remote number code :" + numcode);
        System.out.println("存入redis");

        //返回值
        JSONObject result = new JSONObject();
        result.put("code", numcode);
        return result.toString();
    }
}
