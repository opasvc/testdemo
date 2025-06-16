package com.tencent.user.utlis;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * JWT 工具类
 * 用于生成、验证和解析 JWT token
 * 注意：SECRET_KEY 应该在生产环境中使用更安全的密钥
 */
@Component
public class JwtUtil {
    // JWT 签名密钥，生产环境应该使用更安全的密钥
    private static final String SECRET_KEY = "your-secret-key-should-be-very-long-and-secure";
    // 将密钥转换为 Key 对象
    private static final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    // Token 有效期：5小时
    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000;

    /**
     * 为用户生成 JWT token
     * @param userDetails 用户详情
     * @return JWT token
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    /**
     * 创建 JWT token
     * @param claims token 中的声明
     * @param subject token 的主题（通常是用户名）
     * @return JWT token
     */
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 验证 token 是否有效
     * @param token JWT token
     * @param userDetails 用户详情
     * @return 是否有效
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

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
} 