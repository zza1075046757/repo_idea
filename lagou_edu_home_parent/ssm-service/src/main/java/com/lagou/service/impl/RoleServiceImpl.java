package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.*;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


    //  角色列表查询&条件查询
    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> list = roleMapper.findAllRole(role);
        return list;
    }

    //新增角色
    @Override
    public void saveRole(Role role) {
        //补齐信息
        Date date=new Date();
        role.setCreatedTime(date);
        role.setUpdatedTime(date);

        roleMapper.saveRole(role);
    }

    //修改角色
    @Override
    public void updateRole(Role role) {
        Date date=new Date();
        role.setUpdatedTime(date);

        roleMapper.updateRole(role);

    }

    //根据角色Id查询该角色关联的菜单id信息
    @Override
    public List<Integer> findMenuByRoleId(Integer roleId) {
        List<Integer> list = roleMapper.findMenuByRoleId(roleId);
        return list;
    }

    // 角色、菜单关联
    @Override
    public void roleContextMenu(RoleMenuVO roleMenuVO) {
        //清空中间表关系
        roleMapper.deleteRoleContextMenu(roleMenuVO.getRoleId());

        //循环分配菜单
        for (Integer menuId : roleMenuVO.getMenuIdList()) {
            Role_menu_relation role_menu_relation = new Role_menu_relation();

            role_menu_relation.setRoleId(roleMenuVO.getRoleId());//角色Id
            role_menu_relation.setMenuId(menuId);//菜单Id
            role_menu_relation.setCreatedTime(new Date());
            role_menu_relation.setUpdatedTime(new Date());
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedBy("system");

            roleMapper.roleContextMenu(role_menu_relation);

        }
        
    }

    //删除角色
    @Override
    public void deleteRole(Integer id) {
        //删除中间表关系
        roleMapper.deleteRoleContextMenu(id);
        //删除角色
        roleMapper.deleteRole(id);
    }

    //查询当前角色拥有的资源信息
    @Override
    public List<ResourceCategory>  findResourceListByRoleId(Integer id) {
        //根据角色查询资源分类信息
        List<ResourceCategory> list = roleMapper.findResourceCategoryById(id);
        
        for (ResourceCategory resourceCategory : list) {
            List<Resource> resourceList = roleMapper.findResourceById(resourceCategory.getId());
            resourceCategory.setResourceList(resourceList);
        }
        return list;
    }

    //为角色分配资源
    @Override
    public void roleContextResource(RoleResourceVO resourceVO) {
        //删除中间表原有关系
         roleMapper.deleteRoleResourceRelation(resourceVO.getRoleId());

        //循环集合分配资源
        Date date=new Date();
        for (Integer integer : resourceVO.getResourceIdList()) {
            RoleResourceRelation roleResourceRelation=new RoleResourceRelation();
            roleResourceRelation.setCreatedBy("system");
            roleResourceRelation.setUpdatedBy("system");
            roleResourceRelation.setCreatedTime(date);
            roleResourceRelation.setUpdatedTime(date);
            roleResourceRelation.setRoleId(resourceVO.getRoleId());
            roleResourceRelation.setResourceId(integer);

            roleMapper.roleContextResource(roleResourceRelation);
        }

    }


}
