package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;

import java.util.List;

public interface PromotionAdService {
    /*
    分页查询广告信息
     */
    PageInfo findAllPromotionAdByPage(PromotionAdVO promotionAdVO);

    /*
    新增广告信息
     */
    void   savePromotionAd(PromotionAd promotionAd);

    /*
    更新广告信息
     */
    void updatePromotionAd(PromotionAd promotionAd);

    /*
    回显广告信息
     */
    PromotionAd findPromotionAdById(Integer id);

    /*
    广告动态上下线
    */
    void updatePromotionAdStatus(Integer id,Integer status);

}
