package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //用户分页、多条件组合查询
    @Override
    public PageInfo findAllUserByPage(UserVO userVO) {

        //使用PageHelper
        PageHelper.startPage(userVO.getCurrentPage(), userVO.getPageSize());

        List<User> list = userMapper.findAllUserByPage(userVO);

        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    //用户状态设置
    @Override
    public void updateUserStatus(Integer id, String status) {
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        user.setUpdate_time(new Date());

        userMapper.updateUserStatus(user);
    }

    @Override
    public User login(User user) {
        User userSql = userMapper.login(user);//获取数据库中用户数据(密文)

        //调用MD5验证
        try {
            if (userSql != null && Md5.verify(user.getPassword(), "lagou", userSql.getPassword())) {
                return userSql;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    分配角色回显
     */
    @Override
    public List<Role> findUserRelationById(Integer id) {

        List<Role> list = userMapper.findUserRelationById(id);
        return list;
    }

    @Override
    public void userContextRole(UserVO userVO) {
        //清空中间表
        userMapper.deleteUserContextRole(userVO.getUserId());

        Date date=new Date();
        User_Role_relation user_role_relation=new User_Role_relation();
        for (Integer roleId : userVO.getRoleIdList()) {

            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedBy("system");

            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);

            user_role_relation.setUserId(userVO.getUserId());
            user_role_relation.setRoleId(roleId);

            userMapper.userContextRole(user_role_relation);
        }


        //
    }

    @Override
    public ResponseResult getUserPermissions(Integer id) {
        //1.获取当前用户拥有的角色
        List<Role> roleList = userMapper.findUserRelationById(id);

        System.out.println(roleList);
        //2.获取角色ID,保存到 list
        List<Integer> list = new ArrayList<>();
        for (Role role : roleList) {
            list.add(role.getId());
        }
        System.out.println(list);


        //3.根据角色id查询父级菜单
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(list);
        System.out.println(parentMenu);
        //4.根据父菜单的id封装子菜单
        for (Menu menu : parentMenu) {
            List<Menu> subMenu = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenu);
        }
        //5.获取资源权限
        List<Resource> resourceList = userMapper.findResourceByRoleId(list);

        //6.封装数据
        Map<String,Object> map = new HashMap<>();
        map.put("menuList",parentMenu); //menuList: 菜单权限数据
        map.put("resourceList",resourceList);//resourceList: 资源权限数据
        ResponseResult result = new ResponseResult(true,200,"响应成功",map);
        return result;

    }
}
