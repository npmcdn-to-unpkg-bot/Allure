package com.allure.service.impl;

import com.allure.domain.model.*;
import com.allure.domain.repository.*;
import com.allure.exception.BusinessException;
import com.allure.http.response.album.AlbumVO;
import com.allure.http.response.album.PhotoVO;
import com.allure.http.response.tag.TagVO;
import com.allure.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 8/3/2016.
 */
@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private LikeRecordRepository likeRecordRepository;

    @Autowired
    private CollectionRecordRepository collectionRecordRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Page<AlbumVO> list(Pageable pageable, long tagId) {
        if (pageable == null) {
            pageable = new PageRequest(0, 20, Sort.Direction.DESC, "updateDate");
        }
        Specification<Album> specification = (root, query, criteriaBuilder) -> {
            SetJoin<Album, Tag> join = root.joinSet("tags", JoinType.INNER);
            return criteriaBuilder.equal(join.get("id").as(Long.class), tagId);
        };
        Page<Album> albums = albumRepository.findAll(specification, pageable);
        List<AlbumVO> list = new ArrayList<>();
        for (Album album : albums) {
            list.add(convert(album));
        }
        Page<AlbumVO> page = new PageImpl<>(list, pageable, albums.getTotalElements());
        return page;
    }

    @Transactional
    @Override
    public void delete(long id) {
        Album album = albumRepository.findOne(id);
        if (album == null) throw new BusinessException("album does not exist!");
        albumRepository.delete(id);
    }

    @Transactional
    @Override
    public void likeAlbum(long likerId, long albumId) {
        if (likeRecordRepository.countByAlbumIdAndLikerId(albumId, likerId) > 0) {
            throw new BusinessException("already like this album");
        }
        Album album = albumRepository.findOne(albumId);
        if (album == null) throw new BusinessException("album does not exist");
        Account liker = accountRepository.findOne(likerId);
        if (liker == null) throw new BusinessException("user does not exist");
        LikeRecord likeRecord = new LikeRecord();
        likeRecord.setAlbum(album);
        likeRecord.setLiker(liker);
        likeRecord.setCreateDate(new Date());
        likeRecordRepository.save(likeRecord);
    }

    @Transactional
    @Override
    public void collectAlbum(long collectorId, long albumId) {
        if (collectionRecordRepository.countByAlbumIdAndCollectorId(albumId, collectorId) > 0) {
            throw new BusinessException("already collect this album");
        }
        Album album = albumRepository.findOne(albumId);
        if (album == null) throw new BusinessException("album does not exist");
        Account collector = accountRepository.findOne(collectorId);
        if (collector == null) throw new BusinessException("user does not exist");
        CollectionRecord collectionRecord = new CollectionRecord();
        collectionRecord.setAlbum(album);
        collectionRecord.setCollector(collector);
        collectionRecord.setCreateDate(new Date());
        collectionRecordRepository.save(collectionRecord);
    }

    @Transactional
    @Override
    public void cancelLikeAlbum(long likerId, long albumId) {
        if (likeRecordRepository.countByAlbumIdAndLikerId(albumId, likerId) <= 0) {
            throw new BusinessException("has not liked this album yet!");
        }
        likeRecordRepository.deleteByAlbumIdAndLikerId(albumId, likerId);
    }

    @Transactional
    @Override
    public void cancelCollectAlbum(long collectorId, long albumId) {
        if (collectionRecordRepository.countByAlbumIdAndCollectorId(albumId, collectorId) <= 0) {
            throw new BusinessException("has not collected this album yet!");
        }
        collectionRecordRepository.deleteByAlbumIdAndLikerId(albumId, collectorId);
    }

    @Override
    public boolean liked(long likerId, long albumId) {
        return likeRecordRepository.countByAlbumIdAndLikerId(albumId, likerId) > 0;
    }

    @Override
    public boolean collected(long collectorId, long albumId) {
        return collectionRecordRepository.countByAlbumIdAndCollectorId(albumId, collectorId) > 0;
    }


    private AlbumVO convert(Album album) {
        AlbumVO albumVO = new AlbumVO();
        albumVO.setId(album.getId());
        albumVO.setName(album.getName());
        albumVO.setDescription(album.getDescription());
        albumVO.setCreateDate(album.getCreateDate());
        albumVO.setUpdateDate(album.getUpdateDate());
        albumVO.setCollectionRecordsCount(album.getCollectionRecords().size());
        albumVO.setLikeRecordsCount(album.getLikeRecords().size());
        List<TagVO> tags = new ArrayList<>();
        for (Tag tag : album.getTags()) {
            TagVO tagVO = new TagVO();
            tagVO.setId(tag.getId());
            tagVO.setName(tag.getName());
            tags.add(tagVO);
        }
        albumVO.setTags(tags);
        List<PhotoVO> photos = new ArrayList<>();
        for (Photo photo : album.getPhotos()) {

            PhotoVO photoVO = new PhotoVO();
            photoVO.setId(photo.getId());
            photoVO.setSrc(photo.getSrc());
            photos.add(photoVO);
        }
        albumVO.setPhotos(photos);
        return albumVO;
    }


}
