package com.allure.domain.repository;

import com.allure.domain.BaseRepository;
import com.allure.domain.model.LikeRecord;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 8/3/2016.
 */
public interface LikeRecordRepository extends BaseRepository<LikeRecord> {
    long countByAlbumIdAndLikerId(long albumId, long likerId);

    @Modifying
    @Query("delete from LikeRecord o where o.album.id = ?1 and o.liker.id = ?2")
    @Transactional
    void deleteByAlbumIdAndLikerId(long albumId, long likerId);
}
