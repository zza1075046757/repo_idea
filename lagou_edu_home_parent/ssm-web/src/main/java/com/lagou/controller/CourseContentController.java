package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/courseContent")
@RestController
public class CourseContentController {
    @Autowired
    private CourseContentService courseContentService;

    //展示章节信息
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(Integer courseId) {
        List<CourseSection> list = courseContentService.findSectionAndLessonByCourseId(courseId);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", list);
        return result;
    }

    //回显章节对应的课程信息
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId) {
        Course course = courseContentService.findCourseByCourseId(courseId);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", course);
        return result;
    }

    //增加章节
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection section) {
        if (section.getId() != null) {
            courseContentService.updateSection(section);
        } else {
            courseContentService.saveSection(section);
        }
        return new ResponseResult(true, 200, "响应成功", null);
    }

    /*
    修改章节状态
     */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(Integer id, Integer status) {
        courseContentService.updateSectionStatus(id, status);

        Map<String, Integer> map = new HashMap<>();
        map.put("status", status);
        return new ResponseResult(true, 200, "响应成功", map);
    }

    /*
    新增课时信息
     */
    @RequestMapping("/saveOrUpdateLesson")
    public ResponseResult saveOrUpdateLesson(@RequestBody CourseLesson lesson) {
        if (lesson.getId() != null) {
            courseContentService.updateLesson(lesson);
        } else {
            courseContentService.saveLesson(lesson);
        }
        return new ResponseResult(true, 200, "响应成功", null);
    }


}
