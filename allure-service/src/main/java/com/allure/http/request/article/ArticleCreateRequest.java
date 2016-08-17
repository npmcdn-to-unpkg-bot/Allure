package com.allure.http.request.article;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by yang_shoulai on 2016/8/17.
 */
public class ArticleCreateRequest {

    @NotNull(message = "{article.create.name.empty}")
    @NotBlank(message = "{article.create.name.empty}")
    private String name;

    @NotNull(message = "{article.create.content.empty}")
    @NotBlank(message = "{article.create.content.empty}")
    private String content;

    private long categoryId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
