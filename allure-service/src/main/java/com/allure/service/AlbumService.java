package com.allure.service;

import com.allure.http.response.album.AlbumVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Administrator on 8/3/2016.
 */
public interface AlbumService {

    Page<AlbumVO> list(Pageable pageable, long tagId);

    AlbumVO findById(long id);

    void delete(long id);

    void likeAlbum(long likerId, long albumId);

    void collectAlbum(long collectorId, long albumId);

    void cancelLikeAlbum(long likerId, long albumId);

    void cancelCollectAlbum(long collectorId, long albumId);

    boolean liked(long likerId, long albumId);

    boolean collected(long collectorId, long albumId);

}
