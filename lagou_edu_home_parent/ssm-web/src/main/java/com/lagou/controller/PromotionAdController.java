package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {
    @Autowired
    private PromotionAdService promotionAdService;

    /*
    分页显示广告
     */
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PromotionAdVO promotionAdVO) {
        PageInfo pageInfo = promotionAdService.findAllPromotionAdByPage(promotionAdVO);
        return new ResponseResult(true, 200, "响应成功", pageInfo);
    }

    /*
    图片上传
     */
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult PromotionAdUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        //1.判断文件是否为空
        if (file.isEmpty()) {
            throw new RuntimeException();
        }
        //2.获取原文件名
        String filename = file.getOriginalFilename();
        //3.获取新文件名
        String newFileName =
                System.currentTimeMillis() + filename.substring(filename.lastIndexOf("."));

        //4获取项目在服务器中的绝对路径
        String realPath = request.getServletContext().getRealPath("/");
        //5. 返回上一级目录
        String webappsPath = realPath.substring(0, realPath.indexOf("ssm_web"));

        //6. 定义图片文件
        String uploadPath = webappsPath + "upload\\";
        File filePath = new File(uploadPath, newFileName);

        //如果目录(upload)不存在就创建目录
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录: " + filePath);
        }
        file.transferTo(filePath);

        //将文件名和文件路径返回
        Map<String, String> map = new HashMap<>();
        map.put("fileName", newFileName);
        map.put("filePath", "http://localhost:8080/upload/" + newFileName);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
        return result;

    }


    /*
    新增、更新广告
     */
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd) {
        if (promotionAd.getId() != null) {
            promotionAdService.updatePromotionAd(promotionAd);
        } else {
            promotionAdService.savePromotionAd(promotionAd);
        }
        return new ResponseResult(true, 200, "响应成功", null);
    }

    /*
    回显广告信息
     */
    @RequestMapping("/findPromotionAdById")
    public ResponseResult  findPromotionAdById(Integer id){
        PromotionAd promotionAd = promotionAdService.findPromotionAdById(id);
        return new ResponseResult(true, 200, "响应成功", promotionAd);
    }
    /*
    广告动态上下线
     */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult  updatePromotionAdStatus(Integer id,Integer status){
        promotionAdService.updatePromotionAdStatus(id,status);
        return new ResponseResult(true, 200, "响应成功", status);
    }
}
