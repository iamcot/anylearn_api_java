package com.anylearn.anylearn_api.domain.articles.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.anylearn.anylearn_api.domain.articles.entity.Article;
import com.anylearn.anylearn_api.domain.articles.entity.ArticleTypeEnum;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Long> {

    @Query("SELECT a FROM articles a WHERE type = :type ORDER BY id DESC")
    List<Article> getLastArticles(@Param("type") ArticleTypeEnum typeEnum, Pageable pageable);

    List<Article> findByTypeAndStatusOrderByIdDesc(ArticleTypeEnum typeEnum, boolean status,  Pageable pageable);
}
