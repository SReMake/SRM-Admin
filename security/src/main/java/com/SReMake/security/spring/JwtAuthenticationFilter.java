package com.SReMake.security.spring;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final CustomerUserDetailsService customerUserDetailsService;
    private final JwtConfig jwtConfig;

    public JwtAuthenticationFilter(CustomerUserDetailsService customerUserDetailsService, JwtConfig jwtConfig) {
        this.customerUserDetailsService = customerUserDetailsService;
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {


        String token = extractTokenFromRequest(request);

        if (token != null && validateToken(token)) {
            String username = extractUsernameFromToken(token);

            UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);
            if (userDetails != null) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
        }
        filterChain.doFilter(request, response);
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }

    private boolean validateToken(String token) {
        return JWTUtil.verify(token, jwtConfig.getSecretKey().getBytes(StandardCharsets.UTF_8));
    }

    private String extractUsernameFromToken(String token) {
        JWT jwt = JWTUtil.parseToken(token);
        return (String) jwt.getPayload("username"); //JWT 负载中包含 "username" 字段
    }
}