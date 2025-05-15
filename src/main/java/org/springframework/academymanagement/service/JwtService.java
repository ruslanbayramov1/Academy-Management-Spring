package org.springframework.academymanagement.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.academymanagement.dto.auth.JwtTokenGenerateDTO;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    private final SecretKey key = Keys.hmacShaKeyFor("super-secure-key-super-secure-key-1234!".getBytes());

    public String generateToken(JwtTokenGenerateDTO dto) {
        long now = System.currentTimeMillis();

        Map<String, String> claims = new HashMap<>();
        claims.put("username", dto.username());
        claims.put("email", dto.email());
        claims.put("firstName", dto.firstName());
        claims.put("lastName", dto.lastName());

        return Jwts.builder()
                .subject(dto.username())
                .claims(claims)
                .issuedAt(new Date(now))
                .expiration(new Date(now + 86400000))
                .signWith(key)
                .compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(String token) {
        return parseToken(token).getSubject();
    }

    public String extractEmail(String token) {
        return (String) parseToken(token).get("email");
    }

    public String extractFirstName(String token) {
        return (String) parseToken(token).get("firstName");
    }

    public String extractLastName(String token) {
        return (String) parseToken(token).get("lastName");
    }
}
