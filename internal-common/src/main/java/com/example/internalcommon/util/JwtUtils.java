package com.example.internalcommon.util;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.internalcommon.constant.TokenConstants;
import com.example.internalcommon.dto.TokenResult;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    //盐

    private static final String SIGN = "CPFmsb!@#$$";

    private static final String JWT_KEY_PHONE = "passengerPhone";
    //乘客是1,司机是2
    private static final String JWT_KEY_IDENTITY = "identity";
    //token类型
    private static final String JWT_TOKEN_TYPE = "tokenType";

    //生产token
    public static String generatorToken(String passengerPhone, String identity, String tokenType){
        Map<String,String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE,passengerPhone);
        map.put(JWT_KEY_IDENTITY,identity);
        map.put(JWT_TOKEN_TYPE,tokenType);
        //token 过期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,1);
        Date date = calendar.getTime();

        JWTCreator.Builder builder = JWT.create();
        // 整合Map
        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });
        //整合过期时间
//        builder.withExpiresAt(date);
        //生成token
        String sign = builder.sign(Algorithm.HMAC256(SIGN));
        return sign;
    }


    //解析token
    public static TokenResult parseToken(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        String phoen = verify.getClaim(JWT_KEY_PHONE).asString();
        String identity = verify.getClaim(JWT_KEY_IDENTITY).asString();
        TokenResult result = new TokenResult();
        result.setIdentity(identity);
        result.setPhone(phoen);
        return result;
    }

    /**
     * 校验token
     * @param token
     * @return
     */
    public static TokenResult checkToken(String token){
        //解析token
        TokenResult tokenResult = null;
        try {
            tokenResult = JwtUtils.parseToken(token);
        } catch (Exception e) {

        }
        return tokenResult;
    };
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("name","zhangsan");
        map.put("age","18");
        String s = generatorToken("17691143527","1", TokenConstants.ACCESS_TOKEN_TYPE);
        System.out.println(s);

        TokenResult result = parseToken(s);
        System.out.println("JWT 解析后的token:" + JSON.toJSONString(result));
    }
}
