package com.lagou.service;

import com.lagou.domain.*;

import java.util.List;

public interface RoleService {
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
    角色、菜单关联
     */
    void  roleContextMenu(RoleMenuVO roleMenuVO);

    /*
    删除角色
     */
    void deleteRole(Integer id);

    /*
    查询当前角色拥有的资源信息
    */
    List<ResourceCategory>  findResourceListByRoleId(Integer id);

    /*
   为角色分配资源
     */
    void roleContextResource(RoleResourceVO resourceVO);
}
