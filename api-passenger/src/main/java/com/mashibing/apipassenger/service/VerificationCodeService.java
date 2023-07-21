package com.mashibing.apipassenger.service;

import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.dto.TokenResponse;
import com.example.internalcommon.responese.NumberCodeResponse;
import com.mashibing.apipassenger.remote.ServiceVefificationcodeClient;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.sf.json.JSONObject;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
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

    /**
     * 获取验证码
     * @param passengerPhone 手机号
     * @return
     */
    public ResponseResult generatorCOde(String passengerPhone){
        //调用验证码服务.获取验证码
        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVefificationcodeClient.getNumberCode(6);
        int numcode = numberCodeResponse.getData().getNumberCode();
        log.info("remote number code :" + numcode);
        String key = generatorKeyByPhone(passengerPhone);
        stringRedisTemplate.opsForValue().set(key, numcode + "", 2, TimeUnit.MINUTES);

        //通过短信服务商,将对应的验证码发送到手机上
        return ResponseResult.success(numcode);
    }

    /**
     * 校验验证码
     * @param passengerPhone 手机号
     * @param verificationCode 验证码
     * @return
     */
    public ResponseResult verificationCodeCheck(String passengerPhone, String verificationCode) {
        String key = generatorKeyByPhone(passengerPhone);
        String codeRedis = stringRedisTemplate.opsForValue().get(key);
        log.info("redis value is {}", codeRedis);
        if (!Objects.equals(codeRedis, verificationCode)) {
            return ResponseResult.fail(500, "验证码不正确");
        }
        //判断是否有用户

        //颁发token
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken("token value");
        return ResponseResult.success(tokenResponse);

    }

    private String generatorKeyByPhone(String phone){
        return  verificationCodePrefix + phone;
    }
}
