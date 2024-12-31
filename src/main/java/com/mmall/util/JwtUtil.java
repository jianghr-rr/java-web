package com.mmall.util;

// 生成jwt
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
// 验证
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


public class JwtUtil {
    private static final String SECRET_KEY = "mallAndSecureAndLongAndEnoughAndSecretAndKeyA32bytes"; // 替换为安全的密钥
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1小时有效期

    // 生成 Token
    public static String generateToken(String username) {
        try {
            String token =  Jwts.builder()
                .setSubject(username) // 存储用户名
                .setIssuedAt(new Date()) // 签发时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 过期时间
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 签名算法和密钥
                .compact();
            System.out.println("Generated Token: " + token);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Token generation failed", e);
        }
    }

    // 验证 Token 并获取数据
    public static Claims validateToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY) // 使用相同的密钥
                    .parseClaimsJws(token) // 验证并解析
                    .getBody(); // 返回数据
        } catch (Exception e) {
            throw new RuntimeException("Invalid or expired token");
        }
    }

    // 从请求中提取 authToken Cookie
    public static String extractTokenFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("authToken".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

//    Claims claims = JwtUtil.validateToken(token);
//    String username = claims.getSubject(); // 获取用户名

}


//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//public class JwtUtil {
//    private static final String SECRET_KEY = "@@mall";
//
//    public static String generateToken(String username) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("username", username);
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(username)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 24000 * 60 * 60)) // 24小时后过期
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//                .compact();
//    }
//
//    public static Claims validateToken(String token) {
//        return Jwts.parser()
//                .setSigningKey(SECRET_KEY)
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    public static String extractUsername(String token) {
//        return extractClaim(token, "username");
//    }
//
//    public static <T> T extractClaim(String token, String claim) {
//        return (T) validateToken(token).get(claim);
//    }
//
//    public static Boolean isTokenExpired(String token) {
//        final Claims claims = validateToken(token);
//        return claims.getExpiration().before(new Date());
//    }
//
//}
