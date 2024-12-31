package com.mmall.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmall.util.JwtUtil;
import org.apache.felix.ipojo.annotations.Component;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import io.jsonwebtoken.Claims;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) {
        String path = request.getRequestURI();

        if (path.startsWith("/user/") && !path.equals("/user/login.do") && !path.equals("/user/register.do") && !path.equals("/user/forget_check_answer.do") && !path.equals("/user/forget_reset_password.do")) {

            try {
                // 提取并验证 Token
                String token = JwtUtil.extractTokenFromCookies(request);
                if (token != null) {
                    Claims claims = JwtUtil.validateToken(token);
                    String username = claims.getSubject();
                    // 将用户信息绑定到请求上下文
                    request.setAttribute("username", username);

                    List<SimpleGrantedAuthority> authorities = new ArrayList<>(); // Empty list if no authorities
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
                if (token == null) {
                    throw new RuntimeException("Token not found in cookies");
                }
            } catch (RuntimeException e) {
                handleAuthenticationFailure(response);
    //            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        // 继续处理请求
        try {
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            logger.error("Token validation failed", e);
            throw new RuntimeException(e);
        }
    }


    private void handleAuthenticationFailure(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK); // 设置状态码为 200
        response.setContentType("application/json;charset=UTF-8");

        // 构建响应体
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", 4001); // 自定义业务状态码，401 表示未认证
        responseBody.put("message", "Authentication failed. Please login again.");

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(responseBody);
            response.getWriter().write(jsonResponse);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write response", e);
        }
    }
}
