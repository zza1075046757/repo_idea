package com.lagou.domain;

import java.util.Date;

public class User {

    // 用户id
    private Integer id;
    // 用户昵称
    private String name;
    // 用户头像地址
    private String portrait;
    // 注册手机
    private String phone;
    // 用户密码（可以为空，支持只用验证码注册、登录）
    private String password;
    // 注册ip
    private String reg_ip;
    // 是否有效用户
    private Integer account_non_expired;
    // 账号是否未过期
    private Integer credentials_non_expired;
    // 是否未锁定
    private Integer account_non_locked;
    // 用户状态
    private String status;
    // 是否删除
    private Integer is_del;

    private Date createTime;
    // 更新时间
    private Date updateTime;



    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReg_ip() {
        return reg_ip;
    }

    public void setReg_ip(String reg_ip) {
        this.reg_ip = reg_ip;
    }

    public Integer getAccount_non_expired() {
        return account_non_expired;
    }

    public void setAccount_non_expired(Integer account_non_expired) {
        this.account_non_expired = account_non_expired;
    }

    public Integer getCredentials_non_expired() {
        return credentials_non_expired;
    }

    public void setCredentials_non_expired(Integer credentials_non_expired) {
        this.credentials_non_expired = credentials_non_expired;
    }

    public Integer getAccount_non_locked() {
        return account_non_locked;
    }

    public void setAccount_non_locked(Integer account_non_locked) {
        this.account_non_locked = account_non_locked;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIs_del() {
        return is_del;
    }

    public void setIs_del(Integer is_del) {
        this.is_del = is_del;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", portrait='" + portrait + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", reg_ip='" + reg_ip + '\'' +
                ", account_non_expired=" + account_non_expired +
                ", credentials_non_expired=" + credentials_non_expired +
                ", account_non_locked=" + account_non_locked +
                ", status='" + status + '\'' +
                ", is_del=" + is_del +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
