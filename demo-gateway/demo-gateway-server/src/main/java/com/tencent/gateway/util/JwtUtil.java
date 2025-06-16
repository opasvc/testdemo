package com.tencent.gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

/**
 * JWT 工具类
 * 用于解析和验证 JWT token
 */
@Component
public class JwtUtil {
    // JWT 签名密钥，需要与用户服务中的密钥保持一致
    private static final String SECRET_KEY = "your-secret-key-should-be-very-long-and-secure";
    private static final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    /**
     * 从 token 中提取用户名
     * @param token JWT token
     * @return 用户名
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * 从 token 中提取过期时间
     * @param token JWT token
     * @return 过期时间
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * 从 token 中提取指定的声明
     * @param token JWT token
     * @param claimsResolver 声明解析器
     * @return 声明值
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 从 token 中提取所有声明
     * @param token JWT token
     * @return 所有声明
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 检查 token 是否已过期
     * @param token JWT token
     * @return 是否已过期
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * 验证 token 是否有效
     * @param token JWT token
     * @return 是否有效
     */
    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }
} 