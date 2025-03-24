package com.sreMake.common.exception.business;

import com.sreMake.common.exception.BusinessException;

public class DefaultBusinessException extends RuntimeException implements BusinessException {
    private final int code;

    public DefaultBusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    @Override
    public int code() {
        return code;
    }
}
