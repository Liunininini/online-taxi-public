package com.example.internalcommon.dto;

import com.example.internalcommon.constant.CommonStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResponseResult<T> {

    private int code;
    private String message;
    private T data;

    public static <T> ResponseResult success(T data) {
        return new ResponseResult()
                .setCode(CommonStatusEnum.SUCCESS.getCode())
                .setMessage(CommonStatusEnum.SUCCESS.getValue())
                .setData(data);
    }

    public static <T> ResponseResult success() {
        return new ResponseResult()
                .setCode(CommonStatusEnum.SUCCESS.getCode())
                .setMessage(CommonStatusEnum.SUCCESS.getValue())
                .setData(null);
    }

    /**
     * 失败:自定义失败错误信息
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResponseResult fail(int code, String message) {
        return new ResponseResult()
                .setCode(code)
                .setMessage(message);

    }
    public static <T> ResponseResult fail(int code, String message,String data) {
        return new ResponseResult()
                .setCode(code)
                .setMessage(message)
                .setData(data);

    }
    public static <T> ResponseResult fail(T data) {
        return new ResponseResult().setData(data);

    }
}
