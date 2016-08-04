package com.allure.domain.model;

import com.allure.domain.Model;

import javax.persistence.*;

/**
 * Created by Administrator on 8/3/2016.
 */
@Entity
@Table(name = "t_photo")
public class Photo extends Model {

    @Column(nullable = false)
    private String src;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Album album;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
