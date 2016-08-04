package com.allure;

import com.allure.domain.model.Album;
import com.allure.domain.model.LikeRecord;
import com.allure.domain.model.Tag;
import com.allure.domain.repository.AccountRepository;
import com.allure.domain.repository.AlbumRepository;
import com.allure.domain.repository.LikeRecordRepository;
import com.allure.domain.repository.TagRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
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
}
