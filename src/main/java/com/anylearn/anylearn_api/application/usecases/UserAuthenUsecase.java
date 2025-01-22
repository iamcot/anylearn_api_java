package com.anylearn.anylearn_api.application.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.anylearn.anylearn_api.application.dto.BaseResponseDto;
import com.anylearn.anylearn_api.application.dto.LoginResponseDto;
import com.anylearn.anylearn_api.infra.security.JwtTokenProvider;

@Component
public class UserAuthenUsecase {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    public BaseResponseDto<LoginResponseDto> userLoginToGetToken(String phone, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(phone, password));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            return BaseResponseDto.<LoginResponseDto>builder()
                    .data(new LoginResponseDto(jwtTokenProvider.generateToken(authentication), ""))
                    .build();
        } catch (AuthenticationException e) {
            return BaseResponseDto.<LoginResponseDto>builder()
                    .status(HttpStatus.UNAUTHORIZED)
                    .message(e.getMessage())
                    .build();
        } catch (Exception e) {
            return BaseResponseDto.<LoginResponseDto>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(e.getMessage())
                    .build();
        }
    }

    public Authentication authenticate(UserDetails userDetails, String rawPassword) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDetails.getUsername(), rawPassword));
        return authentication;
    }
}
