package com.anylearn.anylearn_api.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.anylearn.anylearn_api.application.dto.config.AskBoxDto;
import com.anylearn.anylearn_api.domain.articles.entity.Ask;
import com.anylearn.anylearn_api.domain.articles.entity.AskTypeEnum;
import com.anylearn.anylearn_api.domain.articles.repositories.AskRepo;
import com.anylearn.anylearn_api.domain.articles.services.AskService;

@Service
public class AskServiceImpl implements AskService {

    @Autowired
    private AskRepo askRepo;

    @Override
    @Cacheable(value = "ask", key = "'lastQuestion'")
    public Optional<Ask> getLastQuestion() {
        List<Ask> result = askRepo.findLastByTypeAndStatusOrderByIdDesc(AskTypeEnum.question, true,
                PageRequest.of(0, 1));
        return Optional.of(result.getFirst());
    }

    @Override
    public List<Ask> getQuestionActivities(Long questionId, AskTypeEnum type, int size) {
        return askRepo.findByAskIdAndTypeAndStatusOrderByIdDesc(questionId, type, true, PageRequest.of(0, size));
    }

    @Override
    @Cacheable(value = "ask", key = "'questionActivities-' + #question.getId()")
    public AskBoxDto getAskBox(Ask question) {
        AskBoxDto askBox = new AskBoxDto();
        askBox.setQuestion(question);
        askBox.setAnswers(getQuestionActivities(question.getId(), AskTypeEnum.answer, 1));
        askBox.setComments(getQuestionActivities(question.getId(), AskTypeEnum.comment, 1));
        return askBox;
    }

}
