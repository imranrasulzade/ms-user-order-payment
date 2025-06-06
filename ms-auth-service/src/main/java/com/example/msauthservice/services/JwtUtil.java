package com.example.msauthservice.services;

import com.example.msauthservice.entities.AuthUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {

    private final String SECRET = "secret_key"; // Əslində env-dən oxunmalıdır
    private final long EXPIRATION = 86400000; // 1 gün

    public String generateToken(AuthUser user) {
        return Jwts.builder()
                .setSubject(user.getUserId().toString())
                .claim("username", user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }
}
