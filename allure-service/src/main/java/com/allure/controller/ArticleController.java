package com.allure.controller;

import com.allure.domain.model.Article;
import com.allure.domain.model.ArticleCategory;
import com.allure.exception.BusinessException;
import com.allure.http.ApiResponse;
import com.allure.http.request.article.ArticleCreateRequest;
import com.allure.http.response.articles.ArticleCategoryVO;
import com.allure.http.response.articles.ArticleVO;
import com.allure.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yang_shoulai on 2016/8/17.
 */
@RestController
@RequestMapping(value = "/articles")
public class ArticleController extends AbstractBaseController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    @PermitAll
    public ApiResponse list() {
        List<Article> articleList = articleService.findAll();
        return new ApiResponse.Builder().success().result(convert(articleList)).build();

    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    @PermitAll
    public ApiResponse categories() {
        List<ArticleCategory> articleCategories = articleService.findAllCategories();
        List<ArticleCategoryVO> articleCategoryVOList = new ArrayList<>();
        if (articleCategories != null) {
            articleCategories.forEach(articleCategory -> articleCategoryVOList.add(convert(articleCategory)));
        }
        return new ApiResponse.Builder().success().result(articleCategoryVOList).build();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PermitAll
    public ApiResponse article(@PathVariable("id") long id) {
        Article article = articleService.findById(id);
        return new ApiResponse.Builder().success().result(convert(article)).build();

    }


    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public ApiResponse create(@Valid @RequestBody ArticleCreateRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return fromBindingResult(bindingResult);
        }
        ArticleCategory category = articleService.findCategory(request.getCategoryId());
        if (category == null) {
            throw new BusinessException(null, "分类不存在");
        }
        Article article = new Article();
        article.setName(request.getName());
        article.setContent(request.getContent());
        article.setCategory(category);
        articleService.insert(article);
        return new ApiResponse.Builder().success().result(article.getId()).build();

    }


    public static ArticleVO convert(Article article) {
        ArticleVO articleVO = null;
        if (article != null) {
            articleVO = new ArticleVO();
            articleVO.setId(article.getId());
            articleVO.setName(article.getName());
            articleVO.setContent(article.getContent());
            articleVO.setCategory(convert(article.getCategory()));
        }
        return articleVO;
    }

    public static List<ArticleVO> convert(List<Article> articles) {
        List<ArticleVO> articleVOList = null;
        if (articles != null) {
            articles.forEach(article -> articleVOList.add(convert(article)));
        }
        return articleVOList;
    }

    public static ArticleCategoryVO convert(ArticleCategory articleCategory) {
        ArticleCategoryVO articleCategoryVO = null;
        if (articleCategory != null) {
            articleCategoryVO = new ArticleCategoryVO();
            articleCategoryVO.setId(articleCategory.getId());
            articleCategoryVO.setName(articleCategory.getName());
        }
        return articleCategoryVO;
    }

}
