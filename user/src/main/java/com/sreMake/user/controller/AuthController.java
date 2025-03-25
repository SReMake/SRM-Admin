package com.sreMake.user.controller;

import com.sreMake.common.result.ResponseResult;
import com.sreMake.model.user.dto.UserLoginInput;
import com.sreMake.user.service.AuthService;
import com.sreMake.user.vo.CaptchaVo;
import com.sreMake.user.vo.JwtVo;
import jakarta.servlet.http.HttpServletRequest;
import org.babyfish.jimmer.client.EnableImplicitApi;
import org.babyfish.jimmer.client.meta.Api;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@Api
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
    public ResponseResult<JwtVo> login(@RequestBody UserLoginInput user) {
        return ResponseResult.success(authService.login(user));
    }

    /**
     * 免验证码登入
     */
    @PostMapping("/loginWithoutCaptcha")
    public ResponseResult<JwtVo> loginWithoutCaptcha(@RequestBody UserLoginInput user) {
        return ResponseResult.success(authService.loginWithoutCaptcha(user));
    }

    /**
     * 获取验证码
     */
    @GetMapping("/captcha")
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
