package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {

    /*
    多条件课程列表查询
    */
    List<Course> findCourseByCondition(CourseVO courseVo);

    /*
    添加课程、讲师信息
     */
    public void saveCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

    /*
    回显课程信息
     */
    public CourseVO findCourseById(Integer id);

    /*
    更新课程信息
    */
    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

    /*
     更新课程状态
     */
    public void  updateCourseStatus(Integer id,Integer status);


}
