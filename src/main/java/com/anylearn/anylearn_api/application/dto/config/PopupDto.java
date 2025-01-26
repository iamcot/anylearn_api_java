package com.anylearn.anylearn_api.application.dto.config;

import lombok.Data;

@Data
public class PopupDto {
    private String image;

    private String title;

    private String route;

    private String args;

    private Integer version;

    private Boolean status;
}
