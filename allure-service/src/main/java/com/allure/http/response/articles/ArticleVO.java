package com.allure.http.response.articles;

/**
 * Created by yang_shoulai on 2016/8/17.
 */
public class ArticleVO {

    private long id;

    private String name;

    private ArticleCategoryVO category;

    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArticleCategoryVO getCategory() {
        return category;
    }

    public void setCategory(ArticleCategoryVO category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
