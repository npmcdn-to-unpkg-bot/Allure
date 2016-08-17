package com.allure.service;

import com.allure.domain.model.Article;
import com.allure.domain.model.ArticleCategory;

import java.util.List;

/**
 * Created by yang_shoulai on 2016/8/17.
 */
public interface ArticleService {

    Article findById(long id);

    List<Article> findAll();

    void delete(long id);

    List<ArticleCategory> findAllCategories();

    ArticleCategory findCategory(long categoryId);

    void insert(Article article);
}
