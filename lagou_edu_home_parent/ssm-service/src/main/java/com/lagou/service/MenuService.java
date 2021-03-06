package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Menu;
import com.lagou.domain.MenuVO;

import java.util.List;

public interface MenuService {
    /*
    查询所有父子菜单信息
     */
    public List<Menu> findSubMenuListByPid(int pid);

    /*
    查询所有菜单信息
     */
    public PageInfo findAllMenu(MenuVO menuVO);
    /*
    回显具体的菜单信息
     */
    public Menu findMenuById(Integer id);
    /*
    添加菜单信息
     */
    void saveMenu(Menu menu);

    /*
    修改菜单信息
     */
    void updateMenu(Menu menu);

    /*
    删除菜单
    */
    void deleteMenu(Integer id);
}
