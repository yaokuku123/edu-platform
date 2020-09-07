package com.yqj.serviceedu.controller;


import com.yqj.commonutils.R;
import com.yqj.serviceedu.entity.vo.CourseInfo;
import com.yqj.serviceedu.entity.vo.CoursePublishVo;
import com.yqj.serviceedu.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author yqj
 * @since 2020-09-01
 */
@RestController
@RequestMapping("/serviceedu/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    //添加课程基本信息
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfo courseInfo){
        String cid = courseService.addCourseInfo(courseInfo);
        return R.ok().data("courseId",cid);
    }

    //根据id查询课程
    @GetMapping("getCourse/{courseId}")
    public R getCourse(@PathVariable String courseId){
        CourseInfo courseInfo = courseService.getCourseById(courseId);
        return R.ok().data("courseInfo",courseInfo);
    }

    //修改课程信息
    @PostMapping("updateCourse")
    public R updateCourse(@RequestBody CourseInfo courseInfo){
        courseService.updateCourse(courseInfo);
        return R.ok();
    }

    //根据课程id查询课程确认信息
    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id){
        CoursePublishVo coursePublishVo = courseService.publishCourseInfo(id);
        return R.ok().data("publishCourse",coursePublishVo);
    }
}

