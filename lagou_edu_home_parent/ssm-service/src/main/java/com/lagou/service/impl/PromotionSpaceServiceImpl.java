package com.lagou.service.impl;

import com.lagou.dao.PromotionSpaceMapper;
import com.lagou.domain.PromotionSpace;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PromotionSpaceServiceImpl implements PromotionSpaceService {

    @Autowired
    private PromotionSpaceMapper promotionSpaceMapper;

    //回显广告位
    @Override
    public List<PromotionSpace> findAllPromotionSpace() {
        List<PromotionSpace> list = promotionSpaceMapper.findAllPromotionSpace();
        return list;
    }

    @Override
    public void savePromotionSpace(PromotionSpace promotionSpace) {

        //封装数据
        promotionSpace.setSpaceKey(UUID.randomUUID().toString());//广告位ID
        Date date=new Date();
        promotionSpace.setIsDel(0);
        promotionSpace.setCreateTime(date);
        promotionSpace.setUpdateTime(date);

        //执行
        promotionSpaceMapper.savePromotionSpace(promotionSpace);

    }

    //根据ID查广告位
    @Override
    public PromotionSpace findPromotionSpaceById(Integer id) {
        PromotionSpace promotionSpace = promotionSpaceMapper.findPromotionSpaceById(id);
        return promotionSpace;
    }

    //修改广告位名称
    @Override
    public void updatePromotionSpace(PromotionSpace promotionSpace) {

        promotionSpace.setUpdateTime(new Date());
        promotionSpaceMapper.updatePromotionSpace(promotionSpace);
    }
}
