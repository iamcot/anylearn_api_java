package com.anylearn.anylearn_api.domain.articles.services;

import java.util.List;
import java.util.Optional;

import com.anylearn.anylearn_api.application.dto.config.AskBoxDto;
import com.anylearn.anylearn_api.domain.articles.entity.Ask;
import com.anylearn.anylearn_api.domain.articles.entity.AskTypeEnum;

public interface AskService {
    Optional<Ask> getLastQuestion();

    List<Ask> getQuestionActivities(Long questionId, AskTypeEnum type, int size);

    AskBoxDto getAskBox(Ask question);
}
