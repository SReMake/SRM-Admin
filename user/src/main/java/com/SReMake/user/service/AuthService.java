package com.SReMake.user.service;

import com.SReMake.model.user.dto.UserLoginInput;
import com.SReMake.user.vo.CaptchaVo;
import com.SReMake.user.vo.JwtVo;

public interface AuthService {
    /**
     * 登入
     */
    JwtVo login(UserLoginInput loginInput);

    /**
     * 获取验证码
     */
    CaptchaVo captcha();

    /**
     * 注销
     */
    void logout(String token);
}
