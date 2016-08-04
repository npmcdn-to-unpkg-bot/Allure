package com.allure.service;

import com.allure.http.response.tag.TagVO;

import java.util.List;

/**
 * Created by Administrator on 8/3/2016.
 */
public interface TagService {

    List<TagVO> findAll();
}
