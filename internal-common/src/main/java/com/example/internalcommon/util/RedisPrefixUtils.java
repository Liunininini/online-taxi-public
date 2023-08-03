package com.example.internalcommon.util;

public  class RedisPrefixUtils {

    //redis 前缀
    private static final String verificationCodePrefix = "passenger-verification-code-";
    private static final String tokenPrefix = "token-";

    public static String generatorKeyByPhone(String phone) {
        return verificationCodePrefix + phone;
    }

    public static String generatorTokenKey(String phone, String identity) {
        return tokenPrefix + phone + "-" + identity;
    }
}
