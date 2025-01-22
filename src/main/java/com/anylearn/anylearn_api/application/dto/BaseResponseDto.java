package com.anylearn.anylearn_api.application.dto;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseResponseDto<T> {
    private T data;

    @Builder.Default
    private HttpStatus status = HttpStatus.OK;

    private String message;
}
