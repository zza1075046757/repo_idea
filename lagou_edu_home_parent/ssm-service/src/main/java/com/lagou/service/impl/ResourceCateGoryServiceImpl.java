package com.lagou.service.impl;

import com.lagou.dao.ResourceCategoryMapper;
import com.lagou.domain.ResourceCategory;
import com.lagou.service.ResourceCateGoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResourceCateGoryServiceImpl implements ResourceCateGoryService {
    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;

    @Override
    public List<ResourceCategory> findAllResourceCateGory() {

        List<ResourceCategory> list = resourceCategoryMapper.findAllResourceCateGory();
        return list;
    }

    @Override
    public void saveResourceCateGory(ResourceCategory resourceCategory) {
        Date date=new Date();
        resourceCategory.setCreatedTime(date);
        resourceCategory.setUpdatedTime(date);
        resourceCategory.setCreatedBy("system");
        resourceCategory.setUpdatedBy("system");

        resourceCategoryMapper.saveResourceCateGory(resourceCategory);
    }

    @Override
    public void updateResourceCateGory(ResourceCategory resourceCategory) {
        Date date=new Date();
        resourceCategory.setUpdatedTime(date);

        resourceCategoryMapper.updateResourceCateGory(resourceCategory);
    }

    @Override
    public void deleteResourceCateGory(Integer id) {
        resourceCategoryMapper.deleteResourceCateGory(id);
    }
}
