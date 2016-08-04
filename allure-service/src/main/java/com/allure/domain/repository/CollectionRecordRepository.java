package com.allure.domain.repository;

import com.allure.domain.BaseRepository;
import com.allure.domain.model.CollectionRecord;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yang_shoulai on 8/3/2016.
 */
public interface CollectionRecordRepository extends BaseRepository<CollectionRecord> {

    long countByAlbumIdAndCollectorId(Long albumId, long collectorId);

    @Modifying
    @Query("delete from CollectionRecord o where o.album.id = ?1 and o.collector.id = ?2")
    @Transactional
    void deleteByAlbumIdAndLikerId(long albumId, long collectorId);
}
