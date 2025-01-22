package com.anylearn.anylearn_api.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {
    private String token;

    private String refresh = ""; // not implement yet. keep it blank.
}
