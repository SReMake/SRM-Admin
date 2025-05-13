package com.sreMake.security.spring;

import com.sreMake.common.conf.JwtProperties;
import com.sreMake.common.utils.JwtUtils;
import com.sreMake.model.security.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtProperties jwtProperties;

    public JwtAuthenticationFilter(CustomUserDetailsService customUserDetailsService, JwtProperties jwtProperties) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtProperties = jwtProperties;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String resource = request.getRequestURI();
        for (String pattern : SecurityConf.getWhiteListWhitEnv()) {
            if (new AntPathMatcher().match(pattern, resource)) {
                filterChain.doFilter(request, response);
                return;
            }
        }
        String token = JwtUtils.extractTokenFromRequest(request);

        if (token != null && JwtUtils.validateToken(token, jwtProperties.getSecretKey())) {
            String username = JwtUtils.extractUsernameFromToken(token);

            CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            if (userDetails != null) {

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized");
        }
    }

}