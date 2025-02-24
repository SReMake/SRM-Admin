package com.SReMake.user.vo;

import lombok.Data;

@Data
public class JwtVo {
    private String token;
    private long expire;

    public JwtVo(String token, long expire) {
        this.token = token;
        this.expire = expire;
    }
}
