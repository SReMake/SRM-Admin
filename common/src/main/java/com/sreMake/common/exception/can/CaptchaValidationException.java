package com.sreMake.common.exception.can;

import com.sreMake.common.exception.CanThrowException;

public class CaptchaValidationException extends RuntimeException implements CanThrowException {
    public CaptchaValidationException(String message) {
        super(message);
    }
}
