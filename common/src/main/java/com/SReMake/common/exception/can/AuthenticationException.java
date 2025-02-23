package com.SReMake.common.exception.can;

import com.SReMake.common.exception.CanThrowException;

public class AuthenticationException extends org.springframework.security.core.AuthenticationException {
    public AuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public AuthenticationException(String message) {
        super(message);
    }
}
