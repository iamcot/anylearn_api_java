package com.anylearn.anylearn_api.application.dto.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequestDto {
    private String phone;
    private String password;
}
