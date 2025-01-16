package com.anylearn.anylearn_api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.anylearn.anylearn_api.entity.User;
import com.anylearn.anylearn_api.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private UserService userService;

    @BeforeEach
    void setup() {
        User cot = User.builder().name("CoT").phone("0395159198").build();
        Mockito.when(userService.userByPhone(cot.getPhone())).thenReturn(Optional.of(cot));
    }

    @Test
    void giveValidPhone_whenGetProfileByPhone_thenGetUser() throws Exception {
        mvc.perform(get("/profile/0395159198"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", Matchers.is("CoT")));
    }

    @Test
    void giveInvalidPhone_whenGetProfileByPhone_thenNull() throws Exception {
        mvc.perform(get("/profile/123"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenVoid_whenGetTest_thenGetStatus200() throws Exception {

        mvc.perform(get("/test"))
                .andExpect(status().isOk());

    }
}
