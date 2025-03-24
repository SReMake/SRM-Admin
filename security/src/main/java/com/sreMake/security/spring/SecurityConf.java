package com.sreMake.security.spring;

import java.util.List;

public class SecurityConf {
    /**
     * 获取白名单列表
     */

    public static final List<String> WHITE_LIST = List.of(
            "/jimmer-client/**",
            "/openapi.html",
            "/openapi.yml",
            "/api/v1/auth/login",
            "/api/v1/auth/captcha",
            "/api/v1/auth/logout"
    );

}
