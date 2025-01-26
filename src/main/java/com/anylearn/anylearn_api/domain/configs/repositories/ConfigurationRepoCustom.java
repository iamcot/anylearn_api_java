package com.anylearn.anylearn_api.domain.configs.repositories;

import java.util.List;

import com.anylearn.anylearn_api.domain.configs.entity.Configuration;

public interface ConfigurationRepoCustom {
    List<Configuration> findByKeys(List<String> keys);
}
