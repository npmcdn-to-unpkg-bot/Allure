package com.allure.domain.model;

import com.allure.domain.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by yang_shoulai on 2016/8/17.
 */
@Entity
@Table(name = "t_article_category")
public class ArticleCategory extends Model {

    @Column(nullable = false, unique = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
