package com.allure.service.impl;

import com.allure.domain.model.Tag;
import com.allure.domain.repository.TagRepository;
import com.allure.http.response.tag.TagVO;
import com.allure.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 8/3/2016.
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<TagVO> findAll() {
        List<Tag> tags = tagRepository.findAll();
        List<TagVO> tagVOs = new ArrayList<>();
        for (Tag tag : tags) {
            TagVO tagVO = new TagVO();
            tagVO.setId(tag.getId());
            tagVO.setName(tag.getName());
            tagVOs.add(tagVO);
        }
        return tagVOs;
    }
}
