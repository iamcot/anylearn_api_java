package com.anylearn.anylearn_api.application.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.anylearn.anylearn_api.application.dto.config.ConfigHomeDto;
import com.anylearn.anylearn_api.application.usecases.ConfigUsecase;
import com.anylearn.anylearn_api.domain.configs.ConfigurationKeys;
import com.anylearn.anylearn_api.domain.user.services.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class ConfigControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private ConfigUsecase configUsecase;

    @Test
    void givenVoid_whenGetConfigHome_thenGetData() throws Exception {
        ConfigHomeDto configMock = new ConfigHomeDto();
        Mockito.when(configUsecase.configHome(any())).thenReturn(configMock);
        mvc.perform(get("/config/home"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data", Matchers.hasKey("banners")))
                .andExpect(jsonPath("$.data", Matchers.hasKey("ios_transaction")))
                .andExpect(jsonPath("$.data", Matchers.hasKey("quote_url")))
                .andExpect(jsonPath("$.data", Matchers.hasKey("banner_ratio")))
                .andExpect(jsonPath("$.data", Matchers.hasKey("popup")))
                .andExpect(jsonPath("$.data", Matchers.hasKey("articles")))
                .andExpect(jsonPath("$.data", Matchers.hasKey("promotions")))
                .andExpect(jsonPath("$.data", Matchers.hasKey("asks")))
                ;
    }

    // @Test
    // @WithMockUser(username = "1", authorities = {"member"})
    // void giveInvalidPhone_whenGetConfigHome_stillOk() throws Exception {
    //     Mockito.when(configUsecase.configHome(Optional.empty())).thenReturn(ConfigHomeDto.builder()
    //     .banners(null)
    //     .build());
    //     mvc.perform(get("/config/home"))
    //             .andExpect(status().isOk());
    // }
}
