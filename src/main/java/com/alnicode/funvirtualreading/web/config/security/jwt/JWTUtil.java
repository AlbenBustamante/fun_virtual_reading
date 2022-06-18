package com.alnicode.funvirtualreading.web.config.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Here is the utils for the JWT configuration.
 *
 * @author Alben Bustamante
 * @since 1.0
 * @version 1.0
 */
@Component
public class JWTUtil {
    private static final String KEY = "y'_a.t!0";
    private static final long EXPIRATION_TIME = 20 * 1000 * 60;

    /**
     * Get the claims from a token.
     *
     * @param token the JWT
     * @return the claims found
     */
    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }

    /**
     * Get the username from a token.
     *
     * @param token the JWT
     * @return the username found
     */
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    /**
     * Check if the token is expired.
     *
     * @param token the JWT
     * @return {@code true} if already expired
     */
    public boolean isExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    /**
     * Check if the token is valid and is not expired.
     *
     * @param token the JWT
     * @param userDetails the user to check
     * @return {@code true} if is valid
     */
    public boolean isValid(String token, UserDetails userDetails) {
        return getUsername(token).equals(userDetails.getUsername()) && !isExpired(token);
    }

    /**
     * Generate a new token.
     *
     * @param userDetails the user to set
     * @return the JWT
     */
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setSubject(userDetails.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, KEY)
                .compact();
    }

}
