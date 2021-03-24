package com.lagou.controller;

import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceCateGoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCateGoryController {

    @Autowired
    private ResourceCateGoryService resourceCateGoryService;

    //查询资源分类信息
    @RequestMapping("/findAllResourceCategory")
    public ResponseResult  findAllResourceCategory(){
        List<ResourceCategory> list =
                resourceCateGoryService.findAllResourceCateGory();
        return new ResponseResult(true,200,"响应成功",list);
    }


    //添加&修改资源分类
     @RequestMapping("/saveOrUpdateResourceCategory")
    public ResponseResult saveOrUpdateResourceCategory(@RequestBody ResourceCategory resourceCategory){
          if (resourceCategory.getId()!=null){
              resourceCateGoryService.updateResourceCateGory(resourceCategory);
          } else{
              resourceCateGoryService.saveResourceCateGory(resourceCategory);
          }

        return new ResponseResult(true,200,"响应成功",null);
    }

    //删除资源分类信息
    @RequestMapping("/deleteResourceCategory")
    public ResponseResult deleteResourceCategory(Integer id){
        resourceCateGoryService.deleteResourceCateGory(id);
        return new ResponseResult(true,200,"响应成功",null);
    }
}
