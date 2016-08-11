package com.allure;

import com.allure.domain.model.Album;
import com.allure.domain.model.LikeRecord;
import com.allure.domain.model.Photo;
import com.allure.domain.model.Tag;
import com.allure.domain.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false)
@SpringBootTest
public class AllureServiceApplicationTests {

    private static Logger log = LoggerFactory.getLogger(AllureServiceApplicationTests.class);

    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private LikeRecordRepository likeRecordRepository;
    @Autowired
    private
    PhotoRepository photoRepository;

    @Test
    public void contextLoads() {
        Album album = new Album();
        album.setName("AAA");
        album.setCreateDate(new Date());
        album.setUpdateDate(new Date());
        Set<Tag> tagSet = new HashSet<>();
        tagSet.add(tagRepository.findOne(1L));
        tagSet.add(tagRepository.findOne(2L));
        album.setTags(tagSet);
        albumRepository.save(album);

        Set<LikeRecord> likeRecords = new HashSet<>();
        LikeRecord likeRecord1 = new LikeRecord();
        likeRecord1.setAlbum(album);
        likeRecord1.setCreateDate(new Date());
        likeRecord1.setLiker(accountRepository.findOne(1L));
        likeRecordRepository.save(likeRecord1);
        likeRecords.add(likeRecord1);
        album.setLikeRecords(likeRecords);
    }


    @Test
    public void likeRepository() {
        contextLoads();
        likeRecordRepository.deleteByAlbumIdAndLikerId(1, 1);
        long count = likeRecordRepository.countByAlbumIdAndLikerId(1, 1);
        log.debug(count + "");
    }

    @Test
    @Transactional
    public void deleteEmpty() {
        List<Photo> photos = photoRepository.findAll();
        Iterator<Photo> iterable = photos.iterator();
        while (iterable.hasNext()) {
            Photo photo = iterable.next();
            File file = new File("D:\\zeal-albums\\" + photo.getSrc().replace("http://127.0.0.1:8080/allure-image/image/", ""));
            if (!file.exists()) {
                photoRepository.delete(photo.getId());
            }
        }
        List<Album> albums = albumRepository.findAll();
        Iterator<Album> iterator = albums.iterator();
        while (iterator.hasNext()) {
            Album album = iterator.next();
            if (album.getPhotos().isEmpty()) {
                albumRepository.delete(album.getId());
            }
        }
    }


}
