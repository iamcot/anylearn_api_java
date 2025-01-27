package com.anylearn.anylearn_api.application.dto.messages;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailMessage implements Serializable {
    private String to;

    private String subject;

    private String body;
}
