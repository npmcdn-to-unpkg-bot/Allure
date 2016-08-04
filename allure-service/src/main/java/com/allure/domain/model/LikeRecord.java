package com.allure.domain.model;

import com.allure.domain.Model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 8/3/2016.
 */
@Entity
@Table(name = "t_like_record")
public class LikeRecord extends Model {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Account liker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Album album;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    public Account getLiker() {
        return liker;
    }

    public void setLiker(Account liker) {
        this.liker = liker;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
