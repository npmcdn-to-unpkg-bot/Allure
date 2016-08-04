package com.allure.controller;

import com.allure.http.ApiResponse;
import com.allure.service.AlbumService;
import com.allure.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 8/3/2016.
 */
@RestController
@RequestMapping(value = "/albums")
public class AlbumController extends AbstractBaseController {

    @Autowired
    private AlbumService albumService;

    @RequestMapping(method = RequestMethod.GET)
    @PermitAll
    public ApiResponse list(@RequestParam(value = "tagId", defaultValue = "1", required = false) long tagId,
                            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                            @RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize) {
        Pageable pageable = new PageRequest(page, pageSize, Sort.Direction.DESC, "updateDate");
        return new ApiResponse.Builder().success().result(albumService.list(pageable, tagId)).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public ApiResponse delete(@PathVariable("id") long id) {
        albumService.delete(id);
        return new ApiResponse.Builder().success().build();
    }

    @RequestMapping(value = "/{id}/liker", method = RequestMethod.POST)
    @PreAuthorize(value = "hasRole('ROLE_USER')")
    public ApiResponse like(@PathVariable("id") long id) {
        albumService.likeAlbum(getAccountId(), id);
        return new ApiResponse.Builder().success().build();
    }

    @RequestMapping(value = "/{id}/liked", method = RequestMethod.GET)
    @PreAuthorize(value = "hasRole('ROLE_USER')")
    public ApiResponse liked(@PathVariable("id") long id) {
        return new ApiResponse.Builder().success().result(albumService.liked(getAccountId(), id)).build();
    }

    @RequestMapping(value = "/{id}/collection", method = RequestMethod.POST)
    @PreAuthorize(value = "hasRole('ROLE_USER')")
    public ApiResponse collect(@PathVariable("id") long id) {
        albumService.collectAlbum(getAccountId(), id);
        return new ApiResponse.Builder().success().build();
    }

    @RequestMapping(value = "/{id}/collected", method = RequestMethod.GET)
    @PreAuthorize(value = "hasRole('ROLE_USER')")
    public ApiResponse collected(@PathVariable("id") long id) {
        return new ApiResponse.Builder().success().result(albumService.collected(getAccountId(), id)).build();
    }

    @RequestMapping(value = "/{id}/liker", method = RequestMethod.DELETE)
    @PreAuthorize(value = "hasRole('ROLE_USER')")
    public ApiResponse cancelLike(@PathVariable("id") long id) {
        albumService.cancelLikeAlbum(getAccountId(), id);
        return new ApiResponse.Builder().success().build();
    }

    @RequestMapping(value = "/{id}/collection", method = RequestMethod.DELETE)
    @PreAuthorize(value = "hasRole('ROLE_USER')")
    public ApiResponse cancelCollect(@PathVariable("id") long id) {
        albumService.cancelCollectAlbum(getAccountId(), id);
        return new ApiResponse.Builder().success().build();
    }
}
