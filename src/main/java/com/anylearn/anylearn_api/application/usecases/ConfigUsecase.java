package com.anylearn.anylearn_api.application.usecases;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anylearn.anylearn_api.application.dto.config.ConfigHomeDto;
import com.anylearn.anylearn_api.domain.configs.ConfigurationKeys;
import com.anylearn.anylearn_api.domain.configs.services.ConfigurationService;
import com.anylearn.anylearn_api.domain.user.entity.User;

@Service
public class ConfigUsecase {

    @Autowired
    private ConfigurationService configurationService;

    public ConfigHomeDto configHome(Optional<User> user) {
        ConfigHomeDto resp = new ConfigHomeDto();
        HashMap<String, String> configurations = configurationService.getConfigHome();

        try {
            resp.setBannerRatio(Float.parseFloat(configurations.get(ConfigurationKeys.BANNER_RATIO)));
        } catch (NumberFormatException e) {
            resp.setBannerRatio(0.65F);
        }

        resp.setIosTransaction(Boolean.parseBoolean(configurations.get(ConfigurationKeys.CONFIG_IOS_TRANSACTION)));
        resp.setBanners(
                configurationService.parseJsonToBannerList(configurations.get(ConfigurationKeys.CONFIG_APP_BANNERS)));
        resp.setPopup(configurationService.parseJsonToPopup(configurations.get(ConfigurationKeys.CONFIG_HOME_POPUP)));
        return resp;
    }
}
