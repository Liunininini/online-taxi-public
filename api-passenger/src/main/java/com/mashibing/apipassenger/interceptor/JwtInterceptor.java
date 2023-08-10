package com.mashibing.apipassenger.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.internalcommon.constant.TokenConstants;
import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.dto.TokenResult;
import com.example.internalcommon.util.JwtUtils;
import com.example.internalcommon.util.RedisPrefixUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.security.SignatureException;
import java.util.Objects;

public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result = true;
        String resutltString = "";

        String token = request.getHeader("Authorization");
        //解析token
        TokenResult tokenResult = JwtUtils.checkToken(token);
        if (Objects.isNull(tokenResult)){
            resutltString = "token invalid";
            result = false;
        }
//        try {
//             tokenResult = JwtUtils.parseToken(token);
//        } catch (SignatureVerificationException e) {
//            resutltString = "token sign error";
//            result = false;
//        } catch (TokenExpiredException e){
//            resutltString = "token time out";
//            result = false;
//        }catch (AlgorithmMismatchException e){
//            resutltString = "token invalid";
//            result = false;
//        }catch (Exception e){
//            resutltString = "token error";
//            result = false;
//        }
        if (Objects.nonNull(tokenResult)) {
            //从redis中取出token
            String phone = tokenResult.getPhone();
            String identity = tokenResult.getIdentity();
            String tokenKey = RedisPrefixUtils.generatorTokenKey(phone, identity, TokenConstants.ACCESS_TOKEN_TYPE);
            String tokenRedis = redisTemplate.opsForValue().get(tokenKey);
            if (StringUtils.isBlank(tokenRedis) || !token.trim().equals(tokenRedis.trim())) {
                resutltString = "token time out";
                result = false;
            }
        }

        //比较token是否相等
        if (!result){
            PrintWriter out = response.getWriter();
            out.println(JSONObject.toJSONString(ResponseResult.fail(resutltString)));
        }

        return result;
    }
}
