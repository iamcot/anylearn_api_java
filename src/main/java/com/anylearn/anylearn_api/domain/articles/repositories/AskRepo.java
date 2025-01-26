package com.anylearn.anylearn_api.domain.articles.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.anylearn.anylearn_api.domain.articles.entity.Ask;
import com.anylearn.anylearn_api.domain.articles.entity.AskTypeEnum;

public interface AskRepo extends JpaRepository<Ask, Long> {

    List<Ask> findLastByTypeAndStatusOrderByIdDesc(AskTypeEnum type, boolean status, Pageable pageable);    

    List<Ask> findByAskIdAndTypeAndStatusOrderByIdDesc(Long questionId, AskTypeEnum type, boolean status, Pageable pageable);
}
