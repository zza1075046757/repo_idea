package com.lagou.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


public class UserVO {
    private Integer currentPage;
    private Integer pageSize;
    
    private String username;

    @DateTimeFormat(pattern = "yyyy-MM-dd")//指定前台传递数据的格式
    private Date startCreateTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endCreateTime;

    private int userId;
    private List<Integer> roleIdList;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Integer> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Integer> roleIdList) {
        this.roleIdList = roleIdList;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", username='" + username + '\'' +
                ", startCreateTime=" + startCreateTime +
                ", endCreateTime=" + endCreateTime +
                '}';
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getStartCreateTime() {
        return startCreateTime;
    }

    public void setStartCreateTime(Date startCreateTime) {
        this.startCreateTime = startCreateTime;
    }

    public Date getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(Date endCreateTime) {
        this.endCreateTime = endCreateTime;
    }
}
