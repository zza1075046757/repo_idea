package com.lagou.controller;

import com.lagou.domain.*;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    //角色列表查询&条件查询
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role) {
        List<Role> list = roleService.findAllRole(role);
        return new ResponseResult(true, 200, "响应成功", list);
    }

    //添加 修改角色信息
    @RequestMapping("/saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Role role) {
        if (role.getId() != null) {
            roleService.updateRole(role);
        } else {
            roleService.saveRole(role);
        }
        return new ResponseResult(true, 200, "响应成功", null);
    }

    /*
    查询所有父子菜单信息
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu() {
        //-1表示所有的父(子)级菜单
        List<Menu> menuList = menuService.findSubMenuListByPid(-1);

        Map<String, Object> map = new HashMap<>();
        map.put("parentMenuList", menuList);
        return new ResponseResult(true, 200, "响应成功", map);

    }

    /*
      根据角色Id查询该角色关联的菜单id信息
     */
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult  findMenuByRoleId(Integer roleId){
        List<Integer> list = roleService.findMenuByRoleId(roleId);
        return new ResponseResult(true, 200, "响应成功", list);
    }

    /*
    为角色分配菜单信息
     */
    @RequestMapping("/RoleContextMenu")
    public ResponseResult  RoleContextMenu(@RequestBody RoleMenuVO roleMenuVO){
        roleService.roleContextMenu(roleMenuVO);
        return new ResponseResult(true, 200, "响应成功", null);
    }

    /*
    删除角色
     */
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){
        roleService.deleteRole(id);
        return new ResponseResult(true, 200, "响应成功", null);
    }

    /*
    获取当前角色拥有的 资源信息
     */
    @RequestMapping("/findResourceListByRoleId")
    public ResponseResult findResourceListByRoleId(Integer roleId){
        List<ResourceCategory> list = roleService.findResourceListByRoleId(roleId);
        return new ResponseResult(true, 200, "响应成功", list);
    }

    /*
       为角色分配资源
     */
    @RequestMapping("/roleContextResource")
    public ResponseResult roleContextResource(@RequestBody RoleResourceVO resourceVO){
         roleService.roleContextResource(resourceVO);
        return new ResponseResult(true, 200, "响应成功", null);
    }
}
