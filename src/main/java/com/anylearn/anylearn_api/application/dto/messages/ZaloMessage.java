package com.anylearn.anylearn_api.application.dto.messages;

import java.io.Serializable;
import java.util.HashMap;

import lombok.Data;

@Data
public class ZaloMessage implements Serializable {
    private String phone;

    private Long templateId;

    private HashMap<String, String> body;
}
