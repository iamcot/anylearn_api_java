package com.anylearn.anylearn_api.domain.configs.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "configurations")
@Data
public class Configuration {
    @Id
    private String key;

    private String value;

    @Enumerated(EnumType.STRING)
    private ConfigurationTypeEnum type;
    
}
