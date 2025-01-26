package com.anylearn.anylearn_api.domain.configs.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.anylearn.anylearn_api.domain.configs.entity.Configuration;

@Repository
public interface ConfigurationRepo extends CrudRepository<Configuration, Long>, ConfigurationRepoCustom 
{
    
}
