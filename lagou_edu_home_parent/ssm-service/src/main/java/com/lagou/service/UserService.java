package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.*;

import java.util.List;

public interface UserService {
    
    /*
   用户分页、多条件组合查询
    */
    PageInfo findAllUserByPage(UserVO userVO);

    /*
    用户状态设置
    */
    void  updateUserStatus(Integer id,String status);

    /*
    用户登录
     */
    User  login(User user);

    /*
     根据ID查询用户当前角色
     */
    public List<Role> findUserRelationById(Integer id);

    /*
       分配角色
     */
    public void  userContextRole(UserVO userVO);

    /*
     * 获取用户权限
     * */
    ResponseResult getUserPermissions(Integer id);

}
