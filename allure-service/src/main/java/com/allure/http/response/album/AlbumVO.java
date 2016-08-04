package com.allure.http.response.album;

import com.allure.http.response.tag.TagVO;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 8/3/2016.
 */
public class AlbumVO {

    private long id;

    private String name;

    private String description;

    private List<PhotoVO> photos;

    private List<TagVO> tags;

    private Date createDate;

    private Date updateDate;

    private long likeRecordsCount;

    private long collectionRecordsCount;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PhotoVO> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoVO> photos) {
        this.photos = photos;
    }

    public List<TagVO> getTags() {
        return tags;
    }

    public void setTags(List<TagVO> tags) {
        this.tags = tags;
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

    public long getLikeRecordsCount() {
        return likeRecordsCount;
    }

    public void setLikeRecordsCount(long likeRecordsCount) {
        this.likeRecordsCount = likeRecordsCount;
    }

    public long getCollectionRecordsCount() {
        return collectionRecordsCount;
    }

    public void setCollectionRecordsCount(long collectionRecordsCount) {
        this.collectionRecordsCount = collectionRecordsCount;
    }
}
