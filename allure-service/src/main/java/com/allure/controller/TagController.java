package com.allure.controller;

import com.allure.http.ApiResponse;
import com.allure.http.response.tag.TagVO;
import com.allure.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import java.util.List;

/**
 * Created by Administrator on 8/3/2016.
 */
@RestController
@RequestMapping(value = "/tags")
public class TagController extends AbstractBaseController {

    @Autowired
    private TagService tagService;

    @PermitAll
    @RequestMapping(method = RequestMethod.GET)
    public ApiResponse list() {
        List<TagVO> tags = tagService.findAll();
        return new ApiResponse.Builder().success().result(tags).build();
    }
}
