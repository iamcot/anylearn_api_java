package com.anylearn.anylearn_api.domain.articles.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.anylearn.anylearn_api.domain.articles.entity.Article;
import com.anylearn.anylearn_api.domain.articles.entity.ArticleTypeEnum;

public interface ArticleService {
    List<Article> getLastReadArticles(Integer size);

    List<Article> getLastVideoArticles(Integer size);

    List<Article> getLastPromotionArticles(Integer size);

    Article getArticleById(Long id);

    Page<Article> filterArticles(ArticleTypeEnum type, Pageable pageable);

}
