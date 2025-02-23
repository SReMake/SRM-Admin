package com.SReMake.common.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

/**
 * JWT令牌工具类
 */
public class JwtUtil {
    /**
     * 生成jwt
     * 使用Hs256算法, 私匙使用固定秘钥
     *
     * @param secretKey jwt秘钥
     * @param ttlMillis jwt过期时间(毫秒)
     * @param claims    设置的信息
     */
    public static String createJWT(String secretKey, long ttlMillis, Map<String, Object> claims) {
        // 生成JWT的时间
        long expMillis = System.currentTimeMillis() + ttlMillis;
        Date exp = new Date(expMillis);
        // 设置jwt的body
        JwtBuilder builder = Jwts.builder().claims(claims)
                // 设置签名使用的签名算法和签名使用的秘钥
                .signWith(new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256"), Jwts.SIG.HS256).expiration(exp);

        return builder.compact();
    }

    /**
     * Token解密
     *
     * @param secretKey jwt秘钥 此秘钥一定要保留好在服务端, 不能暴露出去, 否则sign就可以被伪造, 如果对接多个客户端建议改造成多个
     * @param token     加密后的token
     */
    public static Claims parseJWT(String secretKey, String token) {
        // 得到DefaultJwtParser
        // 设置签名的秘钥
        // 设置需要解析的jwt
        return Jwts.parser()
                // 设置签名的秘钥
                .verifyWith(new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256"))
                .build().parseSignedClaims(token).getPayload();
    }

}