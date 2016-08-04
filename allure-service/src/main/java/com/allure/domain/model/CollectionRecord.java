package com.allure.domain.model;

import com.allure.domain.Model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 8/3/2016.
 */
@Entity
@Table(name = "t_collection_record")
public class CollectionRecord  extends Model{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Account collector;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Album album;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Account getCollector() {
        return collector;
    }

    public void setCollector(Account collector) {
        this.collector = collector;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
