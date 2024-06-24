package com.dietreino.backend.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dietreino.backend.domain.User;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    @Getter
    private final Integer timeToExpire = 12;
    @Value("${api.security.token.refresh_secret}")
    private String refreshSecret;
    private final String issuer = "dietreino";

    public String generateToken(User user, Boolean refresh) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            return JWT.create()
                    .withIssuer(refresh ? refreshSecret : issuer)
                    .withSubject(user.getEmail())
                    .withClaim("user_id", user.getId().toString())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("JWT generation failed", exception);
        }
    }

    private DecodedJWT decode(String token, Boolean refresh) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            return JWT.require(algorithm)
                    .withIssuer(refresh ? refreshSecret : issuer)
                    .build()
                    .verify(token);
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    public String validateToken(String token, Boolean refresh) {
        DecodedJWT decoded = decode(token, refresh);
        if (decoded == null) {
            return null;
        }

        return decoded.getSubject();
    }

    public Date getExpirationDate(String token, Boolean refresh) {
        DecodedJWT decoded = decode(token, refresh);
        if (decoded == null) {
            return null;
        }

        return decoded.getExpiresAt();
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(timeToExpire).toInstant(ZoneOffset.of("-3"));
    }
}
