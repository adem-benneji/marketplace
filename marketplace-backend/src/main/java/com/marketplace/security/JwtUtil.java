package com.marketplace.security;

import com.marketplace.config.JwtProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    private final JwtProperties jwtProperties;

    public JwtUtil(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String generateToken(String username) {
        byte[] keyBytes = Base64.getDecoder().decode(jwtProperties.getSecret());
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpirationMs()))
                .signWith(Keys.hmacShaKeyFor(keyBytes), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        byte[] keyBytes = Base64.getDecoder().decode(jwtProperties.getSecret());
        Key key = Keys.hmacShaKeyFor(keyBytes);
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(jwtProperties.getSecret());
            Key key = Keys.hmacShaKeyFor(keyBytes);
            Jwts.parserBuilder()
                    .setSigningKey(key) // Use JwtParserBuilder
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
