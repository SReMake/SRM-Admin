package com.SReMake.user.controller;

import com.SReMake.common.result.ResponseResult;
import com.SReMake.model.user.dto.UserLoginInput;
import com.SReMake.user.service.AuthService;
import com.SReMake.user.vo.CaptchaVo;
import com.SReMake.user.vo.JwtVo;
import jakarta.servlet.http.HttpServletRequest;
import org.babyfish.jimmer.client.EnableImplicitApi;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@EnableImplicitApi
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * 登入
     */
    @PostMapping("/login")
    public ResponseResult<JwtVo> login(UserLoginInput user) {
        return ResponseResult.success(authService.login(user));
    }

    /**
     * 获取验证码
     */
    @PostMapping("/captcha")
    public ResponseResult<CaptchaVo> captcha() {
        return ResponseResult.success(authService.captcha());
    }

    /**
     * 注销
     */
    @PostMapping("/logout")
    public ResponseResult<String> logout(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            authService.logout(authorizationHeader.substring(7));
        }
        return ResponseResult.success("OK");
    }
}
