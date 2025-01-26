package com.anylearn.anylearn_api.domain.configs.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.anylearn.anylearn_api.domain.configs.entity.Configuration;

@Repository
public interface ConfigurationRepo extends CrudRepository<Configuration, Long> 
{
    @Query("SELECT c FROM configurations c WHERE key IN (:keys)")
    List<Configuration> findByKeys(@Param("keys") List<String> keys);
}
