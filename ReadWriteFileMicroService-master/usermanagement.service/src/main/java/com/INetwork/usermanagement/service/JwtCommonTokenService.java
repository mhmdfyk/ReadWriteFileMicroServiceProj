package com.INetwork.usermanagement.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Component
@Log4j2
public class JwtCommonTokenService implements Serializable {


    public String GenerateToken(Map<String, Object> claims, String subject, long jwtExpiryDuration, String secret) {
        Key key = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiryDuration))
                .signWith(key).compact();
    }

    public Claims getAllClaimsFromTokenWithoutSecret(String token) {
        int i = token.lastIndexOf('.');
        String withoutSignature = token.substring(0, i + 1);
        return Jwts.parserBuilder().build().parseClaimsJwt(withoutSignature).getBody();
    }

    public String getSubjectFromToken(String token, String secret) {
        String subject;
        try {
            Claims claims = getAllClaimsFromToken(token, secret);
            subject = claims.getSubject();
        } catch (Exception e) {
            subject = null;
        }
        return subject;
    }

    public Boolean isTokenExpired(String token, String secret) {
        final Date expiration = getExpirationDateFromToken(token, secret);
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token, String secret) {
        return getClaimFromToken(token, Claims::getExpiration, secret);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver, String secret) {
        final Claims claims = getAllClaimsFromToken(token, secret);
        return claimsResolver.apply(claims);
    }

    public Claims getAllClaimsFromToken(String token, String secret) {
        Key key = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    private boolean validateToken(String authToken, String secret) {
        try {
            Key key = Keys.hmacShaKeyFor(secret.getBytes());
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
            return true;
        } catch (Exception ex) {
            log.error(ex);
            return false;
        }
    }

    public Optional<String> extractToken(Optional<String> authHeader) {
        if (authHeader.isPresent() && authHeader.get().startsWith("Bearer ")) {
            return Optional.of(authHeader.get().substring(7));
        }
        return Optional.empty();
    }

    public boolean isValidToken(String authToken, String secret) {
        try {
            if (this.validateToken(authToken, secret)
                    && (!this.isTokenExpired(authToken, secret))) {
                return true;
            }
        } catch (Exception ex) {
            log.error(ex);
            return false;
        }
        return false;
    }
}


