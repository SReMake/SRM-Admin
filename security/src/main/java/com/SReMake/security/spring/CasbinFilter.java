package com.SReMake.security.spring;

import com.SReMake.common.conf.JwtConfig;
import com.SReMake.common.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.casbin.jcasbin.main.Enforcer;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
public class CasbinFilter extends OncePerRequestFilter {
    private final Enforcer enforcer;
    private final JwtConfig jwtConfig;

    public CasbinFilter(Enforcer enforcer, JwtConfig jwtConfig) {
        this.enforcer = enforcer;
        this.jwtConfig = jwtConfig;
    }

    @Override
    @SneakyThrows
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) {
        String resource = request.getRequestURI();
        if (SecurityConf.WHITE_LIST.contains(resource)) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = JwtUtils.extractTokenFromRequest(request);

        if (token != null && JwtUtils.validateToken(token, jwtConfig.getSecretKey())) {
            String action = request.getMethod();
            String username = JwtUtils.extractUsernameFromToken(token);
            if (enforcer.enforce(username, action, resource)) {
                filterChain.doFilter(request, response);
            } else {
                // 权限验证失败，返回 403 错误
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("Forbidden");
            }

        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized");
        }
    }
}
