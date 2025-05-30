package com.sreMake.security.exception;

import com.sreMake.common.exception.BusinessException;
import com.sreMake.common.exception.CanThrowException;
import com.sreMake.common.result.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseResult<?> handleRuntimeException(Exception e) {
        //业务异常
        if (e instanceof BusinessException) {
            return ResponseResult.error(((BusinessException) e).code(), e.getMessage());
        }

        // 处理其他异常
        //可返回异常
        if (e instanceof CanThrowException) {
            return ResponseResult.error(400, e.getMessage());
        }
        // 如果是Spring Security的异常，继续抛出以便自定义处理器处理
        if (e instanceof AccessDeniedException || e instanceof AuthenticationException) {
            throw (RuntimeException) e;
        }
        //不可返回异常  记录日志
        log.error(e.getMessage(), e);
        return ResponseResult.error(-999, "操作失败，请稍后重试");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseResult<?> handleException(Exception e) {
        //不可返回异常  记录日志
        log.error(e.getMessage(), e);
        return ResponseResult.error(-999, "操作失败，请稍后重试");
    }
}