package com.sreMake.common.result;

import lombok.Data;

@Data
public class ResponseResult<T> {
    private int code;
    private String message;
    private T data;

    // 带参数的构造函数
    public ResponseResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 成功响应的静态方法
    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(200, "Success", data);
    }

    // 失败响应的静态方法
    public static <T> ResponseResult<T> error(int code, String message) {
        return new ResponseResult<>(code, message, null);
    }
}
