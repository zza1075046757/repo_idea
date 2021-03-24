package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {

    /*
    根据课程ID查询章节信息
     */
    List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);

    /*
    回显章节对应的课程信息
     */
    Course  findCourseByCourseId(Integer courseId);

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
    void updateSectionStatus(CourseSection section);

    /*
    保存课时信息
     */
    void saveLesson(CourseLesson lesson);

    /*
    修改课时信息
     */
    void updateLesson(CourseLesson lesson);
    
}
