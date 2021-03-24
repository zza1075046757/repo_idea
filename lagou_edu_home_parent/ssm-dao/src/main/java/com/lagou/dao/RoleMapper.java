package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface RoleMapper {
    /*
    角色列表查询&条件查询
     */
    List<Role> findAllRole(Role role);

    /*
    添加角色
     */
    void saveRole(Role role);


    /*
    修改角色
     */
    void updateRole(Role role);

    /*
    根据角色Id查询该角色关联的菜单id信息
    */
    List<Integer> findMenuByRoleId(Integer roleId);

    /*
    向中间表添加关联关系时，通常要先把关联管理清空，再添加
    根据roleId 清空关联关系
     */
    void deleteRoleContextMenu(Integer id);

    /*
    角色、菜单关联
     */
    void  roleContextMenu(Role_menu_relation role_menu_relation);

    /*
    删除角色
     */
    void deleteRole(Integer id);

    /*
    查询当前角色拥有的资源分类
    */
    List<ResourceCategory> findResourceCategoryById(Integer id);

    /*
    查询当前角色拥有的资源信息
     */
    List<Resource> findResourceById(Integer id);

    /*
    删除中间表信息
     */
    void deleteRoleResourceRelation(Integer id);

    /*
    角色 资源关联
     */
    void roleContextResource(RoleResourceRelation resourceRelation);
}
