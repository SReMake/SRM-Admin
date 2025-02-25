package com.SReMake.common.exception.can;

import com.SReMake.common.exception.CanThrowException;

public class CaptchaValidationException extends CanThrowException {
    public CaptchaValidationException(String message) {
        super(message);
    }
}
