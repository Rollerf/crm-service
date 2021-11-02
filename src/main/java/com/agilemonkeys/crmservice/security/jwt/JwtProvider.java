package com.agilemonkeys.crmservice.security.jwt;

import com.agilemonkeys.crmservice.security.entity.UserPrincipal;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return Jwts.builder().setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getUserNameFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature.");
            logger.trace(String.format("Invalid JWT signature trace: %s", e.getMessage()));
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token.");
            logger.trace(String.format("Invalid JWT token trace: %s", e.getMessage()));
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token.");
            logger.trace(String.format("Expired JWT token trace: %s", e.getMessage()));
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token.");
            logger.trace(String.format("Unsupported JWT token trace: %s", e.getMessage()));
        } catch (IllegalArgumentException e) {
            logger.error("JWT token compact of handler are invalid.");
            logger.trace(String.format("JWT token compact of handler are invalid trace: %s", e.getMessage()));
        }
        return false;
    }
}
