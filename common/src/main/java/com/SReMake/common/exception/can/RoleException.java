package com.SReMake.common.exception.can;

import com.SReMake.common.exception.CanThrowException;

public class RoleException extends RuntimeException  implements CanThrowException {

    public RoleException(String message) {
        super(message);
    }
}
