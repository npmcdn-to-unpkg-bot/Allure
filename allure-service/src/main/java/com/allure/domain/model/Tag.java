package com.allure.domain.model;

import com.allure.domain.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 8/3/2016.
 */
@Entity
@Table(name = "t_tag")
public class Tag extends Model {

    @Column(nullable = false, unique = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
