package com.steaksandwich.steak_sandwich_backend.session.service;

import java.time.Instant;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;

import java.util.Map;
import java.util.HashMap;
import org.springframework.stereotype.Service;

import java.util.Base64;
import javax.crypto.SecretKey;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class SessionServiceImpl implements SessionService {
  
  private static final String SECRET = "8B3F54FBCC6D3217AB26085703F54CC89E6C87821A2C4F2C9E4F2517584D885EE0691E648993C7C47834E8F312C132769ED6FF1A9A864911D2B1929D9C065A3A";

  private static final int SESSION_TOKEN_LENGTH = 64;

  public String createSession(UserDetails user) {
    Map<String, String> claims = new HashMap<>();
    claims.put("iss", "https://steak-sandwich.com");
    return Jwts.builder()
        .claims(claims)
        .subject(user.getUsername())
        .issuedAt(Date.from(Instant.now()))
        .expiration(Date.from(Instant.now().plus(SESSION_TOKEN_LENGTH, ChronoUnit.HOURS)))
        .signWith(generateKey())
        .compact();
  }

  private SecretKey generateKey() {
    byte[] decodedKey = Base64.getDecoder().decode(SECRET);
    return Keys.hmacShaKeyFor(decodedKey);
  }

  public String extractUsername(String jwt) {
    Claims claims = getClaims(jwt);
    return claims.getSubject();
  }

  private Claims getClaims(String jwt) {
    return Jwts.parser()
        .verifyWith(generateKey())
        .build()
        .parseSignedClaims(jwt)
        .getPayload();
  }

  public boolean validToken(String jwt) {
    Claims claims = getClaims(jwt);
    return claims.getExpiration().after(Date.from(Instant.now()));
  }
  
}
