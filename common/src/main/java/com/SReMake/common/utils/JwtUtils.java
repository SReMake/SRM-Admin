package com.SReMake.common.utils;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;

import java.nio.charset.StandardCharsets;

public class JwtUtils {
    public static String extractTokenFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }

    public static boolean validateToken(String token, String secretKey) {
        if (!JWTUtil.verify(token, secretKey.getBytes(StandardCharsets.UTF_8))) {
            return false;
        }
        JWT jwt = JWTUtil.parseToken(token);
        long expire = Long.parseLong(jwt.getPayload("expire").toString());
        return System.currentTimeMillis() < expire;
    }

    public static String extractUsernameFromToken(String token) {
        JWT jwt = JWTUtil.parseToken(token);
        return (String) jwt.getPayload("username"); //JWT 负载中包含 "username" 字段
    }

    public static String extractUserIdFromToken(String token) {
        JWT jwt = JWTUtil.parseToken(token);
        return (String) jwt.getPayload("id"); //JWT 负载中包含 "username" 字段
    }

}
