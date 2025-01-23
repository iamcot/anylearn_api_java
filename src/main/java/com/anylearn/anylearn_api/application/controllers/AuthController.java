package com.anylearn.anylearn_api.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anylearn.anylearn_api.application.dto.BaseResponseDto;
import com.anylearn.anylearn_api.application.dto.LoginRequestDto;
import com.anylearn.anylearn_api.application.dto.LoginResponseDto;
import com.anylearn.anylearn_api.application.usecases.UserAuthenUsecase;

@RestController()
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserAuthenUsecase userAuthenUsecase;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody() LoginRequestDto loginRequest) {
        BaseResponseDto<LoginResponseDto> response = userAuthenUsecase.userLoginToGetToken(loginRequest.getPhone(),
                loginRequest.getPassword());
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
