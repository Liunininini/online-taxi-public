package com.mashibing.apipassenger.service;

import com.example.internalcommon.constant.IdentityConstant;
import com.example.internalcommon.constant.TokenConstants;
import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.dto.TokenResponse;
import com.example.internalcommon.request.VerificationCodeDTO;
import com.example.internalcommon.responese.NumberCodeResponse;
import com.example.internalcommon.util.JwtUtils;
import com.example.internalcommon.util.RedisPrefixUtils;
import com.mashibing.apipassenger.remote.ServicePassengerUserClient;
import com.mashibing.apipassenger.remote.ServiceVefificationcodeClient;
import lombok.extern.slf4j.Slf4j;
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


    @Resource
    private ServicePassengerUserClient servicePassengerUserClient;

    /**
     * 获取验证码
     *
     * @param passengerPhone 手机号
     * @return
     */
    public ResponseResult generatorCOde(String passengerPhone) {
        //调用验证码服务.获取验证码
        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVefificationcodeClient.getNumberCode(6);
        int numcode = numberCodeResponse.getData().getNumberCode();
        log.info("remote number code :" + numcode);
        String key = RedisPrefixUtils.generatorKeyByPhone(passengerPhone);
        stringRedisTemplate.opsForValue().set(key, numcode + "", 2, TimeUnit.MINUTES);

        //通过短信服务商,将对应的验证码发送到手机上
        return ResponseResult.success(numcode);
    }

    /**
     * 校验验证码
     *
     * @param passengerPhone   手机号
     * @param verificationCode 验证码
     * @return
     */
    public ResponseResult verificationCodeCheck(String passengerPhone, String verificationCode) {
        String key = RedisPrefixUtils.generatorKeyByPhone(passengerPhone);
        String codeRedis = stringRedisTemplate.opsForValue().get(key);
        log.info("redis value is {}", codeRedis);
        if (!Objects.equals(codeRedis, verificationCode)) {
            return ResponseResult.fail(500, "验证码不正确");
        }
        VerificationCodeDTO verificationCodeDTO = new VerificationCodeDTO();
        verificationCodeDTO.setPassengerPhone(passengerPhone);
        //判断是否有用户
        servicePassengerUserClient.loginOrRegister(verificationCodeDTO);
        //颁发token
        String accessToken = JwtUtils.generatorToken(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenConstants.ACCESS_TOKEN_TYPE);
        String refreshToken = JwtUtils.generatorToken(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenConstants.REFRESH_TOKEN_TYPE);


        //将token存到redis中
        String accessTokenKey = RedisPrefixUtils.generatorTokenKey(passengerPhone, IdentityConstant.PASSENGER_IDENTITY,TokenConstants.ACCESS_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(accessTokenKey, accessToken, 30, TimeUnit.DAYS);
        String refreshTokenKey = RedisPrefixUtils.generatorTokenKey(passengerPhone, IdentityConstant.PASSENGER_IDENTITY,TokenConstants.REFRESH_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(refreshTokenKey, refreshToken, 31, TimeUnit.DAYS);
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);
        return ResponseResult.success(tokenResponse);

    }

}
