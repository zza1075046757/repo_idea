package com.lagou.dao;

import com.lagou.domain.ResourceCategory;

import java.util.List;

public interface ResourceCategoryMapper {
    /*
    查询所有资源分类
     */
   public List<ResourceCategory> findAllResourceCateGory();

   /*
   增加资源分类信息
    */
   public void saveResourceCateGory(ResourceCategory resourceCategory);

   /*
   更新资源分类信息
    */
   public void updateResourceCateGory(ResourceCategory resourceCategory);

   /*
   删除资源分类信息
    */
   void deleteResourceCateGory(Integer id);
}
