package com.lagou.dao;

import com.lagou.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {
    /*
    分页查询广告信息
     */
    List<PromotionAd>  findAllPromotionAdByPage();

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
    void updatePromotionAdStatus(PromotionAd promotionAd);

}
