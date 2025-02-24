package com.SReMake.user.enums;

public enum RedisToken {
    AUTH_EXPIRE,
    AUTH_CAPTCHA;

    public String Generate(String payload) {
        return this.name().concat("_").concat(payload);
    }
}
