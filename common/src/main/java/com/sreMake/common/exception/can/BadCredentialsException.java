package com.sreMake.common.exception.can;

import com.sreMake.common.exception.CanThrowException;

public class BadCredentialsException extends org.springframework.security.authentication.BadCredentialsException implements CanThrowException {
    public BadCredentialsException(String message) {
        super(message);
    }
}
