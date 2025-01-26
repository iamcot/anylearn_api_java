package com.anylearn.anylearn_api.application.usecases;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.anylearn.anylearn_api.application.dto.config.AskBoxDto;
import com.anylearn.anylearn_api.application.dto.config.ConfigHomeDto;
import com.anylearn.anylearn_api.domain.articles.entity.Ask;
import com.anylearn.anylearn_api.domain.articles.entity.AskTypeEnum;
import com.anylearn.anylearn_api.domain.articles.services.ArticleService;
import com.anylearn.anylearn_api.domain.articles.services.AskService;
import com.anylearn.anylearn_api.domain.configs.ConfigurationKeys;
import com.anylearn.anylearn_api.domain.configs.services.ConfigurationService;
import com.anylearn.anylearn_api.domain.user.entity.User;

@Service
public class ConfigUsecase {

    public static final float DEFAULT_CONFIG_BANNER_RATIO = 0.56F;
    public static final int DEFAULT_ARTICLE_READ_SIZE = 6;
    public static final int DEFAULT_ARTICLE_PROMOTION_SIZE = 5;

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private AskService askService;

    public ConfigHomeDto configHome(Optional<User> user) {
        ConfigHomeDto resp = new ConfigHomeDto();
        HashMap<String, String> configurations = configurationService.getConfigHome();

        try {
            resp.setBannerRatio(Float.parseFloat(configurations.get(ConfigurationKeys.BANNER_RATIO)));
        } catch (NumberFormatException e) {
            resp.setBannerRatio(DEFAULT_CONFIG_BANNER_RATIO);
        }

        resp.setIosTransaction("1".equals(configurations.get(ConfigurationKeys.CONFIG_IOS_TRANSACTION)));
        resp.setBanners(
                configurationService.parseJsonToBannerList(configurations.get(ConfigurationKeys.CONFIG_APP_BANNERS)));
        resp.setPopup(configurationService.parseJsonToPopup(configurations.get(ConfigurationKeys.CONFIG_HOME_POPUP)));
        resp.setArticles(articleService.getLastReadArticles(DEFAULT_ARTICLE_READ_SIZE));
        resp.setPromotions(articleService.getLastPromotionArticles(DEFAULT_ARTICLE_PROMOTION_SIZE));

        AskBoxDto askBox = new AskBoxDto();
        Optional<Ask> question = askService.getLastQuestion();
        if (question.isPresent()) {
            askBox.setQuestion(question.get());
            askBox.setAnswers(askService.getQuestionActivities(question.get().getId(), AskTypeEnum.answer, 1));
            askBox.setComments(askService.getQuestionActivities(question.get().getId(), AskTypeEnum.comment, 1));
        }
        resp.setAsks(askBox);
        return resp;
    }
}
