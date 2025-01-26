package com.anylearn.anylearn_api.domain.articles.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "asks")
@Data
public class Ask {
    @Id
    private Long id;

    private Long userId;

    private Long askId;

    @Enumerated(EnumType.STRING)
    private AskTypeEnum type;

    private Boolean isSelectedAnswer;

    private Long like;

    private Long unlike;

    private Boolean isProAnswer;

    private Boolean status;

    private String title;

    private String content;

    private Date createdAt;

    private Date updatedAt;
    
}
