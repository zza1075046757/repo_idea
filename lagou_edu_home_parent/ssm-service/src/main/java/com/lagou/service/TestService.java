package com.lagou.service;

import com.lagou.domain.Test;

import java.util.List;

public interface TestService {
    /*
    测试test表
     */
    List<Test> findAllTest();
}
