package com.anylearn.anylearn_api.application.dto.config;

import java.util.List;

import com.anylearn.anylearn_api.domain.articles.entity.Article;
import com.anylearn.anylearn_api.domain.course.entity.Item;

import lombok.Data;

@Data
public class ConfigHomeDto {

    private Boolean iosTransaction;

    private String quoteUrl;

    private Float bannerRatio;

    private String catType;

    private PopupDto popup;

    private List<BannerDto> banners;

    private List<Article> articles;

    private List<Article> promotions;

    private AskBoxDto asks;

    private BoxHasTitleDto<Item> recommendations;

    private List<BoxHasTitleDto<Item>> classes;

    private List<VoucherDto> vouchers;

    private List<Item> j4u;

    private List<String> pointBox;

    private List<Item> repurchases;
}
