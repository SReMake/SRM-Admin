package com.SReMake.user.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.jwt.JWTUtil;
import com.SReMake.common.conf.JwtConfig;
import com.SReMake.common.exception.can.CaptchaValidationException;
import com.SReMake.model.user.User;
import com.SReMake.model.user.dto.UserLoginInput;
import com.SReMake.repository.user.UserRepository;
import com.SReMake.user.enums.RedisToken;
import com.SReMake.user.service.AuthService;
import com.SReMake.user.vo.CaptchaVo;
import com.SReMake.user.vo.JwtVo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    private final JwtConfig jwtConfig;

    private final RedisTemplate<String, Object> redisTemplate;

    public AuthServiceImpl(UserRepository userRepository, JwtConfig jwtConfig, RedisTemplate<String, Object> redisTemplate) {
        this.userRepository = userRepository;
        this.jwtConfig = jwtConfig;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public JwtVo login(UserLoginInput loginInput) {
        String captcha = String.valueOf(redisTemplate.opsForValue().getAndDelete(RedisToken.AUTH_CAPTCHA.Generate(loginInput.getCaptchaId())));
        if (!captcha.equals(loginInput.getCaptcha())) {
            throw new CaptchaValidationException("wrong captcha!");
        }

        User user = userRepository.findByUsername(loginInput.getUsername());
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("User not found!");
        }
        if (!BCrypt.checkpw(loginInput.getPassword(), user.password())) {
            throw new BadCredentialsException("wrong username or password!");
        }

        byte[] key = jwtConfig.getSecretKey().getBytes(StandardCharsets.UTF_8);
        long expire = System.currentTimeMillis() + jwtConfig.getExpireTime();
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", user.id());
        payload.put("expire", expire);
        payload.put("username", user.username());
        String token = JWTUtil.createToken(payload, key);
        return new JwtVo(token, expire);
    }

    @Override
    public CaptchaVo captcha() {
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 100, 4, 4);
        String uuid = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(RedisToken.AUTH_CAPTCHA.Generate(uuid), captcha.getCode(), 60, TimeUnit.SECONDS);
        return new CaptchaVo(uuid, captcha.getImageBase64Data());
    }

    @Override
    public void logout(String token) {
        long expire = Long.parseLong(JWTUtil.parseToken(token).getPayload("expire").toString());
        if (System.currentTimeMillis() < expire) {
            redisTemplate.opsForValue().set(RedisToken.AUTH_EXPIRE.Generate(token), "1", expire - System.currentTimeMillis() + 1000, TimeUnit.MILLISECONDS);
        }
    }
}
