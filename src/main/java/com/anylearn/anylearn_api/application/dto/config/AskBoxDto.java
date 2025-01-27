package com.anylearn.anylearn_api.application.dto.config;

import java.io.Serializable;
import java.util.List;

import com.anylearn.anylearn_api.domain.articles.entity.Ask;

import lombok.Data;

@Data
public class AskBoxDto implements Serializable {
    private Ask question;

    private List<Ask> comments;

    private List<Ask> answers;
}
