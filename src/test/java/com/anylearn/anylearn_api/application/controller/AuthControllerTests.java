package com.anylearn.anylearn_api.application.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.anylearn.anylearn_api.application.dto.BaseResponseDto;
import com.anylearn.anylearn_api.application.dto.auth.LoginRequestDto;
import com.anylearn.anylearn_api.application.dto.auth.LoginResponseDto;
import com.anylearn.anylearn_api.application.usecases.UserAuthenUsecase;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserAuthenUsecase authenUsecase;

    @BeforeEach
    void setup() {
    }

    @Test
    void givenInValidCredentials_whenLogin_thenReturn404() throws Exception {
        when(authenUsecase.userLoginToGetToken(Mockito.contains("1"), Mockito.anyString()))
        .thenReturn(BaseResponseDto.<LoginResponseDto>builder().status(HttpStatus.NOT_FOUND).build());

        mockMvc.perform(post("/auth/login")
        .content(new ObjectMapper().writeValueAsString(LoginRequestDto.builder().phone("1").password("xxx").build()))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
    }

    @Test
    void givenValidCredentials_whenLogin_thenReturnToken() throws Exception {
        when(authenUsecase.userLoginToGetToken(Mockito.anyString(), Mockito.anyString()))
        .thenReturn(BaseResponseDto.<LoginResponseDto>builder().status(HttpStatus.OK).data(new LoginResponseDto("jwt-token", "")).build());

        mockMvc.perform(post("/auth/login")
        .content(new ObjectMapper().writeValueAsString(LoginRequestDto.builder().phone("0395399198").password("xxx").build()))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data.token", Matchers.is("jwt-token")))
        .andExpect(jsonPath("$.data.refresh", Matchers.is("")));
    }
}
