package com.sreMake.user.vo;

import lombok.Data;

@Data
public class CaptchaVo {
    private String captcha;
    private String id;

    public CaptchaVo(String id, String captcha) {
        this.id = id;
        this.captcha = captcha;
    }
}
