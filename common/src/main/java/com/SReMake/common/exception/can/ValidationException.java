package com.SReMake.common.exception.can;

import com.SReMake.common.exception.CanThrowException;

public class ValidationException extends RuntimeException implements CanThrowException {
    public ValidationException() {
        super("ValidationException");
    }

    public ValidationException(String message) {
        super(message);
    }
}
