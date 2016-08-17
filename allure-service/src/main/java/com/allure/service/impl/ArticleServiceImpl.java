package com.allure.service.impl;

import com.allure.domain.model.Article;
import com.allure.domain.model.ArticleCategory;
import com.allure.domain.repository.ArticleCategoryRepository;
import com.allure.domain.repository.ArticleRepository;
import com.allure.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yang_shoulai on 2016/8/17.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleCategoryRepository articleCategoryRepository;

    @Override
    public Article findById(long id) {
        return articleRepository.findOne(id);
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(long id) {
        articleRepository.delete(id);
    }

    @Override
    public List<ArticleCategory> findAllCategories() {
        return articleCategoryRepository.findAll();
    }

    @Override
    public ArticleCategory findCategory(long categoryId) {

        return articleCategoryRepository.findOne(categoryId);
    }

    @Override
    @Transactional
    public void insert(Article article) {
        articleRepository.save(article);
    }
}
