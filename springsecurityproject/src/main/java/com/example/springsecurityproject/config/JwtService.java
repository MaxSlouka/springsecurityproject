package com.example.springsecurityproject.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "NB/Z&}0E#Ql!4_W&e1e#5);Fge5<+wy-~`7I;~n[s0e65|fKv<NKTE1Lvb>Rd~N";

    public String extractUserEmail(String token) {
    return extractClaim(token, Claims::getSubject);
    }

        public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
        }
        private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        }

    private Key getSigningKey() {
        byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyByte);
    }
}

