package com.lagou.service.impl;

import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;
import com.lagou.service.CourseService;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findCourseByCondition(CourseVO courseVo) {
        List<Course> courseList = courseMapper.findCourseByCondition(courseVo);
        return courseList;
    }

    @Override
    public void saveCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {
      //封装课程信息
      Course course=new Course();
      BeanUtils.copyProperties(course,courseVO);

      //补齐信息（日期）
        Date date=new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);

        //保存课程
        courseMapper.saveCourse(course);

        //取出selectKey保存到实体的ID
        int id = course.getId();

        //封装讲师信息
        Teacher teacher=new Teacher();
        BeanUtils.copyProperties(teacher,courseVO);

        //补齐信息
        teacher.setCourseId(id);
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setIsDel(0);

        //保存讲师信息
        courseMapper.saveTeacher(teacher);
    }

    @Override
    public CourseVO findCourseById(Integer id) {
       return courseMapper.findCourseById(id);
    }

    @Override
    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {
        //封装课程信息
        Course course=new Course();
        BeanUtils.copyProperties(course,courseVO);

        //补齐信息
        Date date=new Date();
        course.setUpdateTime(date);

        //更新课程
        courseMapper.updateCourse(course);

        //封装讲师信息
        Teacher teacher=new Teacher();
        BeanUtils.copyProperties(teacher,courseVO);

        //2.补齐信息
        teacher.setUpdateTime(date);
        teacher.setCourseId(course.getId()); //补齐讲师中的course_id

        //更新
        courseMapper.updateTeacher(teacher);
    }

    @Override
    public void updateCourseStatus(Integer id, Integer status) {
        Course course=new Course();
        course.setId(id);
        course.setStatus(status);

        //补齐信息
        Date date=new Date();
        course.setUpdateTime(date);

        //更新
        courseMapper.updateCourseStatus(course);
    }


}
