package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentService {
    /*
    根据课程ID查询章节(课时)信息
     */
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);

    /*
    回显章节对应的课程信息
    */
    public Course findCourseByCourseId(Integer courseId);

    /*
    保存章节
    */
    void saveSection(CourseSection section);

    /*
    修改章节
    */
    void updateSection(CourseSection section);

    /*
    修改章节状态
    */
    void updateSectionStatus(Integer id,Integer status);

    /*
    新增课时
     */
    void saveLesson(CourseLesson lesson);

    /*
    更新章节信息
    */
    void updateLesson(CourseLesson lesson);
}
