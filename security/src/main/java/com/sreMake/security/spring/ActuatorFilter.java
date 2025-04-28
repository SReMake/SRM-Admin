package com.sreMake.security.spring;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@Component
public class ActuatorFilter extends OncePerRequestFilter {
    @Override
    @SneakyThrows
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) {
        String resource = request.getRequestURI();
        for (String pattern : SecurityConf.getActuatorList()) {
            if (new AntPathMatcher().match(pattern, resource)) {
//                判断请求来源是不是局域网而不是公网
                String clientIp = getClientIpAddress(request);
                if (isInternalIp(clientIp)) {
                    // 内网请求处理逻辑
                    filterChain.doFilter(request, response);
                } else {
                    // 外网请求处理逻辑
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Unauthorized");
                    return;
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private String getClientIpAddress(HttpServletRequest request) {
        // 尝试从代理头中获取 IP 地址
        String ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.hasText(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (!StringUtils.hasText(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (!StringUtils.hasText(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (!StringUtils.hasText(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        // 如果仍未获取到有效 IP 地址，则使用 request.getRemoteAddr()
        if (!StringUtils.hasText(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            log.warn("No proxy headers found, using remote address: {}", ip);
        }

        // 处理多级代理的情况
        if (StringUtils.hasText(ip) && ip.contains(",")) {
            ip = ip.split(",")[0].trim(); // 取第一个 IP 地址
        }

        return ip;
    }

    private boolean isInternalIp(String ipAddress) {
        try {
            if (ipAddress == null || ipAddress.isEmpty()) {
                return false;
            }

            // 特殊处理 localhost
            if ("127.0.0.1".equals(ipAddress) || "0:0:0:0:0:0:0:1".equals(ipAddress) || "::1".equals(ipAddress)) {
                return true;
            }

            InetAddress inetAddress = InetAddress.getByName(ipAddress);
            byte[] ipBytes = inetAddress.getAddress();
            if (ipBytes.length == 4) {
                // 检查IPv4地址
                int firstOctet = ipBytes[0] & 0xFF;
                int secondOctet = ipBytes[1] & 0xFF;
                if (firstOctet == 10) {
                    return true;
                } else if (firstOctet == 172 && secondOctet >= 16 && secondOctet <= 31) {
                    return true;
                } else if (firstOctet == 192 && secondOctet == 168) {
                    return true;
                }
            }
        } catch (UnknownHostException e) {
            log.error("Failed to determine if IP address is internal", e);
        }
        return false;
    }
}
