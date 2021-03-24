package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /*
    多条件课程列表查询
    */
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO) {
        List<Course> courseList = courseService.findCourseByCondition(courseVO);
        ResponseResult result = new ResponseResult(true, 0, "响应成功", courseList);
        return result;
    }

    /*
    课程图片上传
     */
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException {
        //1.判断文件是否为空
        if (file.isEmpty()) {
            throw new RuntimeException();
        }
        //2.获取原文件名 abc.jpg
        String filename = file.getOriginalFilename();
        System.out.println(filename);
        //3.获取新文件名123456.jpg
        String newFileName =
                System.currentTimeMillis() + filename.substring(filename.lastIndexOf("."));

        //4获取项目在服务器中的绝对路径  D:\apache-tomcat-8.5.56\webapps\ssm_web\
        String realPath = request.getServletContext().getRealPath("/");
        //5. 返回上一级目录 D:\apache-tomcat-8.5.56\webapps\
        String webappsPath=realPath.substring(0,realPath.indexOf("ssm_web"));

        //6. 定义图片文件
       String uploadPath=webappsPath+"upload\\";
        File filePath=new File(uploadPath,newFileName);

        //如果目录(upload)不存在就创建目录
        if(!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录: " + filePath);
        }
        file.transferTo(filePath);

        //将文件名和文件路径返回
        Map<String,String> map = new HashMap<>();
        map.put("fileName",newFileName);
        map.put("filePath","http://localhost:8080/upload/"+newFileName);
        ResponseResult result = new ResponseResult(true,200,"响应成功",map);
        return result;

    }

    /*
    新增、修改 课程、讲师信息

     */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {
         if (courseVO.getId()==null){
             courseService.saveCourseOrTeacher(courseVO);
         }  else{
             courseService.updateCourseOrTeacher(courseVO);
         }
        ResponseResult result = new ResponseResult(true, 200, "响应成功", null);
        return result;
    }

    /*
    回显课程信息
     */
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){
        CourseVO courseVO = courseService.findCourseById(id);
        ResponseResult result = new ResponseResult(true,200,"响应成功",courseVO);
        return result;
    }

    /*
    更新课程状态
     */
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(int id,int status){
        courseService.updateCourseStatus(id,status);

        Map<String,Integer> map=new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
        return result;
    }
 
}
