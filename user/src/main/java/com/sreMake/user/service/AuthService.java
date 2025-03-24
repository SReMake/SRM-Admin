package com.sreMake.user.service;

import com.sreMake.model.user.dto.UserLoginInput;
import com.sreMake.user.vo.CaptchaVo;
import com.sreMake.user.vo.JwtVo;

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
