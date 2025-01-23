package com.anylearn.anylearn_api.application.controller;

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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.anylearn.anylearn_api.domain.user.entity.User;
import com.anylearn.anylearn_api.domain.user.services.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private UserService userService;

    @BeforeEach
    void setup() {
        User cot = User.builder().name("CoT").phone("0395359198").build();
        Mockito.when(userService.userByPhone(cot.getPhone())).thenReturn(Optional.of(cot));
    }

    @Test
    @WithMockUser(username = "0395359198", authorities = {"member"})
    void giveValidPhone_whenGetProfileByPhone_thenGetUser() throws Exception {
        mvc.perform(get("/user/profile"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", Matchers.is("CoT")));
    }

    @Test
    @WithMockUser(username = "1", authorities = {"member"})
    void giveInvalidPhone_whenGetProfileByPhone_then404() throws Exception {
        mvc.perform(get("/profile"))
                .andExpect(status().isNotFound());
    }

    @Test
    void giveNoToken_whenGetProfile_thenError403() throws Exception {
        mvc.perform(get("/user/profile"))
                .andExpect(status().is4xxClientError());

    }
}
