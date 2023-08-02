package com.example.internalcommon.util;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.internalcommon.dto.TokenResult;
import net.sf.json.util.JSONUtils;

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

    //生产token
    public static String generatorTOken(String passengerPhone,String identity){
        Map<String,String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE,passengerPhone);
        map.put(JWT_KEY_IDENTITY,identity);
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
        builder.withExpiresAt(date);
        //生成token
        String sign = builder.sign(Algorithm.HMAC256(SIGN));
        return sign;
    }


    //解析token
    public static TokenResult parseToken(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        String phoen = verify.getClaim(JWT_KEY_PHONE).toString();
        String identity = verify.getClaim(JWT_KEY_IDENTITY).toString();
        TokenResult result = new TokenResult();
        result.setIdentity(identity);
        result.setPhone(phoen);
        return result;
    }

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("name","zhangsan");
        map.put("age","18");
        String s = generatorTOken("17691143527","1");
        System.out.println(s);

        TokenResult result = parseToken(s);
        System.out.println("JWT 解析后的token:" + JSON.toJSONString(result));
    }
}
