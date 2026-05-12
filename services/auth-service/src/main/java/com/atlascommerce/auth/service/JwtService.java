package com.atlascommerce.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service  //new kullanmamak için kullanılan bir şey
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.access-expiration}")
    private long accessExpiration;

    @Value("${jwt.refresh-expiration}")
    private long refreshExpiration;

    public String generateAccessToken(String email) {
        return buildToken(email, accessExpiration);
    }

    public String generateRefreshToken(String email) {
        return buildToken(email, refreshExpiration);
    }

    public String extractEmail(String token) {
        return extractClaims(token).getSubject(); // emailden konuyu çekme
    }

    public boolean isTokenValid(String token) {
        return extractClaims(token).getExpiration().after(new Date());
    }

    private String buildToken(String email, long expiration) {   // token uretir
        return Jwts.builder()
                .subject(email) //token kimin
                .issuedAt(new Date())  // şuan uretildi
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignKey())  //secret ile imzala
                .compact(); // çevir
    }

    private Claims extractClaims(String token) {   // imzayı dogrulama gibi
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }








}
