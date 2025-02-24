package com.SReMake.user.controller;

import org.babyfish.jimmer.client.EnableImplicitApi;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@EnableImplicitApi
public class AuthController {
    /**
     * 登入
     */
    @PostMapping("/login")
    public String login(UserLoginInput user) {
        if ("admin".equals(username) && "123456".equals(password)) {
            return "success";
        }
        return "fail";
    }

    /**
     * 获取验证码
     */
    @PostMapping("/captcha")
    public String captcha() {
        return "success";
    }

    /**
     * 注销
     */
    @PostMapping("/logout")
    public String logout() {
        return "success";
    }
}
