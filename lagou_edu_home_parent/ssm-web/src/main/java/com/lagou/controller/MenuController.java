package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    /*
    查询所有菜单列表信息
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu() {
        List<Menu> list = menuService.findAllMenu();
        return new ResponseResult(true, 200, "响应成功", list);
    }

    /*
    回显菜单信息
     */
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id){
        Map<String,Object> map = new HashMap<>();

        if (id==-1){
            //添加 回显信息中不需要查询
            List<Menu> list = menuService.findSubMenuListByPid(-1);

            //封装数据
            map.put("menuInfo",null);
            map.put("parentMenuList",list);
        }
        else{
            //修改操作 回显所有的Menu信息
            List<Menu> list = menuService.findSubMenuListByPid(-1);
            Menu menu = menuService.findMenuById(id);
            //封装数据
            map.put("menuInfo",menu);
            map.put("parentMenuList",list);
        }
        return new ResponseResult(true,200,"响应成功",map);
        
    }


    /*
    添加、修改菜单
     */
    @RequestMapping("/saveOrUpdateMenu")
    public ResponseResult saveOrUpdateMenu(@RequestBody Menu menu){
        if (menu.getId()!=null){
            //更新
                menuService.updateMenu(menu);
        }   else{
            //新增
            menuService.saveMenu(menu);
        }
        return new ResponseResult(true, 200, "响应成功", null);
    }
    /*
    删除菜单
     */
    @RequestMapping("/deleteMenu")
    public ResponseResult deleteMenu(Integer id){
        menuService.deleteMenu(id);
        return new ResponseResult(true, 200, "响应成功", null);
    }
}
