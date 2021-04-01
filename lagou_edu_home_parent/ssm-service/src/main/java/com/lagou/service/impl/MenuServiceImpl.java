package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import com.lagou.domain.MenuVO;
import com.lagou.domain.User;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findSubMenuListByPid(int pid) {
        List<Menu> subMenuListByPid = menuMapper.findSubMenuListByPid(pid);
        return subMenuListByPid ;
    }

    @Override
    public PageInfo findAllMenu(MenuVO menuVO) {

        //使用PageHelper
        PageHelper.startPage(menuVO.getCurrentPage(), menuVO.getPageSize());
        List<Menu> allMenu = menuMapper.findAllMenu(menuVO);

        PageInfo<Menu> pageInfo = new PageInfo<>(allMenu);
        return pageInfo;
    }

    @Override
    public Menu findMenuById(Integer id) {
        return  menuMapper.findMenuById(id);
    }

    @Override
    public void saveMenu(Menu menu) {
        Date date=new Date();
        menu.setCreatedTime(date);
        menu.setUpdatedTime(date);
        menu.setCreatedBy("system");
        menu.setUpdatedBy("system");
        menuMapper.saveMenu(menu);

    }

    @Override
    public void updateMenu(Menu menu) {
        menu.setUpdatedTime(new Date());
        menuMapper.updateMenu(menu);
    }

    @Override
    public void deleteMenu(Integer id) {
        menuMapper.deleteMenu(id);
    }
}
