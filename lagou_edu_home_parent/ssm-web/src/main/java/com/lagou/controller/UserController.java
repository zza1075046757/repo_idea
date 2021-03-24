package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /*
    用户分页、多条件组合查询
    */
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVO userVO) {
        PageInfo pageInfo = userService.findAllUserByPage(userVO);
        return new ResponseResult(true, 200, "响应成功", pageInfo);
    }

    /*
    更新用户状态
     */
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(Integer id, String status) {
        userService.updateUserStatus(id, status);
        return new ResponseResult(true, 200, "响应成功", status);
    }

    /*
    用户登录
     */
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) {
        User user1 = userService.login(user);
        if (user1 != null) {
            //保存用户ID、access_token到session中
            HttpSession session = request.getSession();
            String access_token = UUID.randomUUID().toString();
            session.setAttribute("user_id", user1.getId());
            session.setAttribute("access_token", access_token);

            Map<String, Object> map = new HashMap<>();
            map.put("access_token", access_token);
            map.put("user_id", user1.getId());
            map.put("user",user1);//将user存入session(登出时使用)
            return new ResponseResult(true, 1, "success", map);
        } else {
            return new ResponseResult(true, 400, "用户名密码错误", null);

        }
    }

    /*
    分配角色回显
     */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(Integer id){
        List<Role> list = userService.findUserRelationById(id);
        return new ResponseResult(true, 200, "响应成功", list);
    }

    /*
    分配角色
     */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVO userVO){
        userService.userContextRole(userVO);
        return new ResponseResult(true, 200, "响应成功", null);
    }


    /*
      获取用户权限
     */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){
        //获取请求头中的token  "Authorization"
        String header_token = request.getHeader("Authorization");

        //获取session中的token
        HttpSession session= request.getSession();
        String access_token = (String)session.getAttribute("access_token");


        //判断token是否一致
        if (header_token.equalsIgnoreCase(access_token)){
            Integer id = (Integer)session.getAttribute("user_id");
            ResponseResult result = userService.getUserPermissions(id);
            return  result;
        } else{
            return  new ResponseResult(false,400,"获取失败",null);
        }
    }
}
