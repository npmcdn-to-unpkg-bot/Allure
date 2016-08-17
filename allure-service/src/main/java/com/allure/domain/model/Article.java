package com.allure.domain.model;

import com.allure.domain.Model;

import javax.persistence.*;

/**
 * Created by yang_shoulai on 2016/8/17.
 */
@Entity
@Table(name = "t_article")
public class Article extends Model {

    @Column(nullable = false)
    private String name;

    @Lob
    @Column(nullable = false)
    @Basic(fetch = FetchType.LAZY)
    private String content;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category")
    private ArticleCategory category;

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

    public ArticleCategory getCategory() {
        return category;
    }

    public void setCategory(ArticleCategory category) {
        this.category = category;
    }
}
