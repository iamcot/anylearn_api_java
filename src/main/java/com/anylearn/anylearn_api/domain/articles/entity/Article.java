package com.anylearn.anylearn_api.domain.articles.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "articles")
@Data
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private ArticleTypeEnum type;

    private String title;

    private String image;

    private String video;

    private String shortContent;

    private Long view;

    private Long like;

    private Boolean status;

    private Boolean isHot;

    private Date createdAt;

    private Date updatedAt;

    private String tags;
}
