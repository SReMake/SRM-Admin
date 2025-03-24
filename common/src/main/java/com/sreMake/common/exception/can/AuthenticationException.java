package com.sreMake.common.exception.can;

import com.sreMake.common.exception.CanThrowException;

public class AuthenticationException extends org.springframework.security.core.AuthenticationException implements CanThrowException {
    public AuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException() {
        super("Access Denied");
    }
}
