package com.mashibing.apipassenger.service;

import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.responese.NumberCodeResponse;
import com.mashibing.apipassenger.remote.ServiceVefificationcodeClient;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.sf.json.JSONObject;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class VerificationCodeService {

    @Resource
    private ServiceVefificationcodeClient serviceVefificationcodeClient;

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    //redis 前缀
    private String  verificationCodePrefix = "passenger-verification-code-";
    public ResponseResult generatorCOde(String passengerPhone){
        //调用验证码服务.获取验证码
        System.out.println("调用验证码服务.获取验证码");

        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVefificationcodeClient.getNumberCode(6);
        int numcode = numberCodeResponse.getData().getNumberCode();
        log.info("remote number code :" + numcode);
        String key = verificationCodePrefix + passengerPhone;
        stringRedisTemplate.opsForValue().set(key, numcode + "", 2, TimeUnit.MINUTES);

        //通过短信服务商,将对应的验证码发送到手机上
        return ResponseResult.success(numcode);
    }
}
