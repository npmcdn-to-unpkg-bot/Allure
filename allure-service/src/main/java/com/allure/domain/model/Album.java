package com.allure.domain.model;

import com.allure.domain.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Administrator on 8/3/2016.
 */
@Entity
@Table(name = "t_album")
public class Album extends Model {

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "album", cascade = {CascadeType.REMOVE})
    private Set<Photo> photos;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "t_album_tags",
            joinColumns = {@JoinColumn(name = "album")},
            inverseJoinColumns = {@JoinColumn(name = "tag")})
    private Set<Tag> tags;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "album", cascade = {CascadeType.REMOVE})
    private Set<LikeRecord> likeRecords;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "album", cascade = {CascadeType.REMOVE})
    private Set<CollectionRecord> collectionRecords;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<LikeRecord> getLikeRecords() {
        return likeRecords;
    }

    public void setLikeRecords(Set<LikeRecord> likeRecords) {
        this.likeRecords = likeRecords;
    }

    public Set<CollectionRecord> getCollectionRecords() {
        return collectionRecords;
    }

    public void setCollectionRecords(Set<CollectionRecord> collectionRecords) {
        this.collectionRecords = collectionRecords;
    }
}
