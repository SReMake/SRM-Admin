package com.SReMake.security.spring;

import com.SReMake.security.casbin.CasbinFilter;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CasbinFilter casbinFilter;

    public SpringSecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, CasbinFilter casbinFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.casbinFilter = casbinFilter;
    }

    @SneakyThrows
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(conf -> conf.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // 配置放行路径

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        SecurityConf.WHITE_LIST.toArray(new String[0])
                )
                .permitAll()
                .anyRequest().authenticated()
        );
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(casbinFilter, JwtAuthenticationFilter.class);
        return http.build();
    }
}