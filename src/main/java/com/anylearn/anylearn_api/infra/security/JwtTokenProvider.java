package com.anylearn.anylearn_api.infra.security;

import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtTokenProvider {

    @Value("${security.jwtSecret}")
    private String jwtSecret;
    @Value("${security.jwtExpInMs}")
    private long jwtExpInMs;
    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer";

    public String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date exp = new Date(now.getTime() + jwtExpInMs);

        Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
        claims.put("role", userDetails.getAuthorities());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length() + 1);
        }
        return "";
    }

    public Claims resolveClaims(HttpServletRequest request) throws ExpiredJwtException {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(resolveToken(request))
                    .getBody();
        } catch (ExpiredJwtException e) {
            request.setAttribute("expired", e.getMessage());
            throw e;
        } catch (Exception e) {
            request.setAttribute("invalid", e.getMessage());
            throw e;
        } 
    }

    public boolean validateClaims(Claims claims) throws AuthenticationException {
        try {
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            throw e;
        }
    }

    public String getPhone(Claims claims) {
        return claims.getSubject();
    }
}
