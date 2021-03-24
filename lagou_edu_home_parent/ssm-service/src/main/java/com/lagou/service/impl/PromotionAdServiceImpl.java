package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.PromotionAdMapper;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PromotionAdServiceImpl implements PromotionAdService {
    @Autowired
    private PromotionAdMapper promotionAdMapper;


    //分页显示广告
    @Override
    public PageInfo findAllPromotionAdByPage(PromotionAdVO promotionAdVO) {
        PageHelper.startPage(promotionAdVO.getCurrentPage(),promotionAdVO.getPageSize());
        List<PromotionAd> list = promotionAdMapper.findAllPromotionAdByPage();

        //获取分页其他参数 将上述集合传入
        PageInfo<PromotionAd> pageInfo = new PageInfo<>(list);
        return pageInfo ;
    }

    //新增广告
    @Override
    public void savePromotionAd(PromotionAd promotionAd) {
        Date date=new Date();
        promotionAd.setCreateTime(date);
        promotionAd.setUpdateTime(date);

        promotionAdMapper.savePromotionAd(promotionAd);
    }
     //更新广告
    @Override
    public void updatePromotionAd(PromotionAd promotionAd) {

        Date date=new Date();
        promotionAd.setUpdateTime(date);

        promotionAdMapper.updatePromotionAd(promotionAd);
    }

    //回显广告信息
    @Override
    public PromotionAd findPromotionAdById(Integer id) {

        PromotionAd promotionAd = promotionAdMapper.findPromotionAdById(id);
        return promotionAd;
    }

    //广告动态上下线
    @Override
    public void updatePromotionAdStatus(Integer id,Integer status) {
        PromotionAd promotionAd=new PromotionAd();
        promotionAd.setId(id);
        promotionAd.setStatus(status);
       promotionAd.setUpdateTime(new Date());
       promotionAdMapper.updatePromotionAdStatus(promotionAd);
    }
}
