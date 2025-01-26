package com.anylearn.anylearn_api.application.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anylearn.anylearn_api.application.dto.config.BannerDto;
import com.anylearn.anylearn_api.application.dto.config.PopupDto;
import com.anylearn.anylearn_api.domain.configs.ConfigurationKeys;
import com.anylearn.anylearn_api.domain.configs.entity.Configuration;
import com.anylearn.anylearn_api.domain.configs.repositories.ConfigurationRepo;
import com.anylearn.anylearn_api.domain.configs.services.ConfigurationService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {

    @Autowired
    private ConfigurationRepo configurationRepo;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public HashMap<String, String> getConfigHome() {
        HashMap<String, String> homeConfigs = new HashMap<>();

        try {
            List<Configuration> configurations = configurationRepo.findByKeys(ConfigurationKeys.getHomeConfigKeys());
            for (Configuration config : configurations) {
                homeConfigs.put(config.getKey(), config.getValue());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        ConfigurationKeys.getHomeConfigKeys().forEach(k -> homeConfigs.putIfAbsent(k, ""));
        return homeConfigs;
    }

    @Override
    public List<BannerDto> parseJsonToBannerList(String json) {
        try {
            HashMap<Integer, BannerDto> res = mapper.readValue(json, new TypeReference<HashMap<Integer, BannerDto>>() {
            });
            return res.values().stream().toList();
        } catch (Exception e) {
            System.err.println(e);
        }
        return Collections.emptyList();

    }

    @Override
    public PopupDto parseJsonToPopup(String json) {
        try {
            return mapper.readValue(json, new TypeReference<PopupDto>() { });
        } catch (Exception e) {
            return null;
        }
    }

}
