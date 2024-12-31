package com.mmall.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    // 设置 Token 到 Cookie
    public static void setAuthTokenCookie(String token) {
        HttpServletResponse response = getCurrentResponse();
        if (response != null) {
            Cookie cookie = new Cookie("authToken", token);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setPath("/");
            cookie.setMaxAge(3600); // 1小时有效期
            response.addCookie(cookie);
        }
    }

    // 获取当前 HttpServletResponse
    private static HttpServletResponse getCurrentResponse() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes) requestAttributes).getResponse();
        }
        return null;
    }


    // 清空指定名称的 Cookie
    public static void clearCookie() {
        HttpServletResponse response = getCurrentResponse();
        if (response != null) {
            Cookie cookie = new Cookie("authToken", null);
            cookie.setPath("/"); // 确保与设置时的 Path 一致
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setMaxAge(0); // 立即过期
            response.addCookie(cookie);
        }
    }
}
