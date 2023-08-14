package com.example.internalcommon.constant;

import lombok.Data;
import lombok.Getter;


public enum CommonStatusEnum {

    /**
     * Token类提示1100-1199
     */
    TOKEN_ERROR(1199, "token错误"),

    /**
     * 用户错误提示1200-1299
     */
    USER_NOT_EXIST(1299, "当前用户不存在"),
    SUCCESS(1, "success"),
    FAIL(0, "fail");

    @Getter
    private int code;
    @Getter
    private String value;

    CommonStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }


}
