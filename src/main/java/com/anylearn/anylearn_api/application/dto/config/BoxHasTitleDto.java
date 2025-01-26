package com.anylearn.anylearn_api.application.dto.config;

import java.util.List;

import lombok.Data;

@Data
public class BoxHasTitleDto<T> {
    private String route;

    private String title;

    private List<T> items;
}
