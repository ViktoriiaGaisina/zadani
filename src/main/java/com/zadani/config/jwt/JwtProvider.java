package com.zadani.config.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    /*
    генерация токена для определенного логина пользователя
     */
    public String generateToken(String login) {
        return Jwts.builder()
            .setSubject(login)
            .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
            .signWith(jwtSecretKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    /*
   проверка токена что он годен к использованию
    */
    public Boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecretKey()).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Неверный JWT токен : {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("Срок действия JWT токена истек: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT токен не поддерживается: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("Строка JWT пуста: {}", e.getMessage());
        }
        return false;
    }

    public String getLoginByToken(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(jwtSecretKey())
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    /*
    для проверки подписи токена или для подписи токена
     */
    private Key jwtSecretKey() {
        return Keys.hmacShaKeyFor(Base64.getEncoder().encode(jwtSecret.getBytes()));
    }
}
