package com.sreMake.security.spring

import com.sreMake.common.conf.AppENV
import com.sreMake.common.utils.logger

object SecurityConf {
    /**
     * 获取白名单列表
     */

    @JvmStatic
    val authList: List<String> = listOf(
        "/api/v1/auth/login",
        "/api/v1/auth/captcha",
        "/api/v1/auth/logout",
        "/api/v1/auth/loginWithoutCaptcha"
    )


    @JvmStatic
    val actuatorList: List<String> = listOf("/actuator/**")


    @JvmStatic
    val jimmerOpenapi: List<String> = listOf(
        "/jimmer-client/**",
        "/openapi.html",
        "/openapi.yml",
        "/ts.zip"
    )

    @JvmStatic
    val whiteListWhitEnv: List<String> = System.getenv("APP_ENV")?.let {
        if (it.equals(AppENV.PROD.name, ignoreCase = true)) {
            mutableListOf<String>().apply {
                addAll(authList)
                addAll(actuatorList)
            }
        } else {
            mutableListOf<String>().apply {
                addAll(authList)
                addAll(jimmerOpenapi)
                addAll(actuatorList)
            }
        }
    } ?: authList.also {
        logger().warn("event 'APP_ENV' not set default use prod white list")
    }


}