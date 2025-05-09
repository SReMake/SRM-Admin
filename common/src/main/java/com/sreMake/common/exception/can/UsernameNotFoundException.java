package com.sreMake.common.exception.can;

import com.sreMake.common.exception.CanThrowException;

public class UsernameNotFoundException extends org.springframework.security.core.userdetails.UsernameNotFoundException implements CanThrowException {
    public UsernameNotFoundException(String message) {
        super(message);
    }
}
