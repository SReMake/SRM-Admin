package com.sreMake.security.spring;

import com.sreMake.common.conf.AppENV;

import java.util.List;
import java.util.stream.Stream;

public class SecurityConf {
    /**
     * 获取白名单列表
     */

    public static final List<String> AUTH_LIST = List.of(
            "/api/v1/auth/login",
            "/api/v1/auth/captcha",
            "/api/v1/auth/logout"
    );

    public static final List<String> ACTUATOR_LIST = List.of("/actuator/**");

    public static final List<String> JIMMER_OPENAPI = List.of(
            "/jimmer-client/**",
            "/openapi.html",
            "/openapi.yml"
    );

    public static final List<String> WHITE_LIST_WHIT_ENV = System.getenv("APP_ENV").equalsIgnoreCase(AppENV.PROD.name()) ? AUTH_LIST : Stream.concat(Stream.concat(AUTH_LIST.stream(), JIMMER_OPENAPI.stream()), ACTUATOR_LIST.stream()).toList();


}
