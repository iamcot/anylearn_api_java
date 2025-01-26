package com.anylearn.anylearn_api.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.anylearn.anylearn_api.domain.articles.entity.Article;
import com.anylearn.anylearn_api.domain.articles.entity.ArticleTypeEnum;
import com.anylearn.anylearn_api.domain.articles.repositories.ArticleRepo;
import com.anylearn.anylearn_api.domain.articles.services.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepo articleRepo;

    @Override
    public List<Article> getLastReadArticles(Integer size) {
        return articleRepo.findByTypeAndStatusOrderByIdDesc(ArticleTypeEnum.read, true, PageRequest.of(0, size));
    }

    @Override
    public List<Article> getLastVideoArticles(Integer size) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLastVideoArticles'");
    }

    @Override
    public Article getArticleById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getArticleById'");
    }

    @Override
    public Page<Article> filterArticles(ArticleTypeEnum type, Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filterArticles'");
    }

    @Override
    public List<Article> getLastPromotionArticles(Integer size) {
        return articleRepo.findByTypeAndStatusOrderByIdDesc(ArticleTypeEnum.promotion, true, PageRequest.of(0, size));
    }
    
}
