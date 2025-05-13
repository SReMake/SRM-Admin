package com.sreMake.security.casbin;

import com.sreMake.common.conf.JwtProperties;
import com.sreMake.common.utils.JwtUtils;
import com.sreMake.security.spring.SecurityConf;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.casbin.jcasbin.main.Enforcer;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
public class CasbinFilter extends OncePerRequestFilter {
    private final Enforcer enforcer;
    private final JwtProperties jwtProperties;
    private final AntPathMatcher matcher = new AntPathMatcher();

    public CasbinFilter(Enforcer enforcer, JwtProperties jwtProperties) {
        this.enforcer = enforcer;
        this.jwtProperties = jwtProperties;
    }

    @Override
    @SneakyThrows
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) {
        String resource = request.getRequestURI();
        for (String pattern : SecurityConf.getWhiteListWhitEnv()) {
            if (matcher.match(pattern, resource)) {
                filterChain.doFilter(request, response);
                return;
            }
        }
        String token = JwtUtils.extractTokenFromRequest(request);

        if (token != null && JwtUtils.validateToken(token, jwtProperties.getSecretKey())) {
            String action = request.getMethod();
            String username = JwtUtils.extractUsernameFromToken(token);
//            是管理员就跳过
            if (username.equals("admin") || enforcer.getRolesForUser(username).contains("administrator")) {
                filterChain.doFilter(request, response);
                return;
            }
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
