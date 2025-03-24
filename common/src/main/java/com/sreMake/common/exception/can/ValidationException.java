package com.sreMake.common.exception.can;

import com.sreMake.common.exception.CanThrowException;

public class ValidationException extends RuntimeException implements CanThrowException {
    public ValidationException() {
        super("ValidationException");
    }

    public ValidationException(String message) {
        super(message);
    }
}
