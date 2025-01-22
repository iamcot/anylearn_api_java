package com.anylearn.anylearn_api.infra.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtTokenProvider jwtTokenProvider;

    private UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws IOException, ServletException {
        try {
            String token = jwtTokenProvider.resolveToken(request);

            if (token.isEmpty()) {
                throw new Exception("no tokem");
            }

            Claims claims = jwtTokenProvider.resolveClaims(request);
            if (claims != null && jwtTokenProvider.validateClaims(claims)) {
                String phone = jwtTokenProvider.getPhone(claims);
                UserDetails userDetails = userDetailsService.loadUserByUsername(phone);
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                        userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            // response.setStatus(HttpStatus.UNAUTHORIZED.value());
            // response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            // ObjectMapper mapper = new ObjectMapper();
            // Map<String, String> errorResponse = new HashMap<>();
            // errorResponse.put("message", "Token is not valid");
            // mapper.writeValue(response.getWriter(), errorResponse);
            // return;
        }
        filterChain.doFilter(request, response);
    }
}
