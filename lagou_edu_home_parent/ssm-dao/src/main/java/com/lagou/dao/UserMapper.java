package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface UserMapper {
    /*
    用户分页、多条件组合查询
     */
    List<User> findAllUserByPage(UserVO userVO);

    /*
    用户状态设置
     */
    void  updateUserStatus(User user);

    /*
    用户登录
     */
    User  login(User user);



    /*
    删除用户角色中间表关系
     */
    public void deleteUserContextRole(Integer id);

    /*
       分配角色
     */
    public void  userContextRole(User_Role_relation user_role_relation);

    /*
     根据ID查询用户当前角色
     */
    public List<Role> findUserRelationById(Integer id);

    /**
     * 根据角色id,查询角色拥有的顶级菜单信息
     * */
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);
    /**
     * 根据PID 查询子菜单信息
     * */
    public List<Menu> findSubMenuByPid(int pid);
    /**
     * 获取用户拥有的资源权限信息
     * */
    public List<Resource> findResourceByRoleId(List<Integer> ids);


}
