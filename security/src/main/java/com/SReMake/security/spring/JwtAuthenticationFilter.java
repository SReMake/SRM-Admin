package com.SReMake.security.spring;

import com.SReMake.common.conf.JwtConfig;
import com.SReMake.common.utils.JwtUtils;
import com.SReMake.model.security.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtConfig jwtConfig;

    public JwtAuthenticationFilter(CustomUserDetailsService customUserDetailsService, JwtConfig jwtConfig) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String resource = request.getRequestURI();
        if (SecurityConf.WHITE_LIST.contains(resource)) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = JwtUtils.extractTokenFromRequest(request);

        if (token != null && JwtUtils.validateToken(token, jwtConfig.getSecretKey())) {
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