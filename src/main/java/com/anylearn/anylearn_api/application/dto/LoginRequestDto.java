package com.anylearn.anylearn_api.application.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String phone;
    private String password;
}
