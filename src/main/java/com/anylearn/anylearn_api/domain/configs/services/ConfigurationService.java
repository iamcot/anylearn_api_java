package com.anylearn.anylearn_api.domain.configs.services;

import java.util.HashMap;
import java.util.List;

import com.anylearn.anylearn_api.application.dto.config.BannerDto;
import com.anylearn.anylearn_api.application.dto.config.PopupDto;

public interface ConfigurationService {
    HashMap<String, String> getConfigHome();

    List<BannerDto> parseJsonToBannerList(String json);

    PopupDto parseJsonToPopup(String json);
}
