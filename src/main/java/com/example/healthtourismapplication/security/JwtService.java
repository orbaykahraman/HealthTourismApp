package com.example.healthtourismapplication.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "o6aa26z6sHvNt0OuBaHoMkdksCOT8GLYHEYdIxlEiudqm3Cv6nYq2DOidNnhUFJCPWb8a1IEMNgWS0NNHlcrwRSyKO1VlNmWXrdfjdhBgoFPmnJeJ1PENf2RqXu6uOgrHGpynE6OWbs6NPFwGzerPrCESz2nEl2kIgO5SdZ6794DvqR9J1s6Rj1FK9ObdQL6h4Llkln6trgL9RljaSbel1TmxQtLfFh8BWCMHYfSRwbZbNi3kW83EnuPItB29PEu";
    public String extractUserName(String token) {
        return  null;
    }

    public  <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractALLClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractALLClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
