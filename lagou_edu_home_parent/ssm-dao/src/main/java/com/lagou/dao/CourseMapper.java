package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;

import java.util.List;

public interface CourseMapper {
    /*
    多条件课程列表查询
     */
    List<Course> findCourseByCondition(CourseVO courseVo);

    /*
    新增课程信息
     */
    void saveCourse(Course course);

    /*
    新增讲师信息
     */
    void saveTeacher(Teacher teacher);

    /*
    根据id回显课程信息
     */
    CourseVO findCourseById(Integer id);

    /*
    更新课程信息
     */
    void updateCourse(Course course);

    /*
     更新讲师信息
     */
    void updateTeacher(Teacher teacher);

    /*
    修改课程状态
     */
    void updateCourseStatus(Course course);


}
