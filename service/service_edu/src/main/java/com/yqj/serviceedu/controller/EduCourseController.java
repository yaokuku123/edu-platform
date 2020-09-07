package com.yqj.serviceedu.controller;


import com.yqj.commonutils.R;
import com.yqj.serviceedu.entity.EduCourse;
import com.yqj.serviceedu.entity.vo.CourseInfo;
import com.yqj.serviceedu.entity.vo.CoursePublishVo;
import com.yqj.serviceedu.entity.vo.CourseQuery;
import com.yqj.serviceedu.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //发布课程
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal"); //表示课程发布
        courseService.updateById(eduCourse);
        return R.ok();
    }

    //课程列表分页查询
    @PostMapping("pageCourse/{current}/{limit}")
    public R pageCourse(@PathVariable long current, @PathVariable long limit,
                        @RequestBody(required = false) CourseQuery courseQuery){
        Page<EduCourse> pageCourse = new Page<>(current, limit);
        courseService.pageQuery(pageCourse,courseQuery);
        long total = pageCourse.getTotal();
        List<EduCourse> courseList = pageCourse.getRecords();
        return R.ok().data("total",total).data("rows",courseList);
    }

    //根据课程id删除课程信息
    @DeleteMapping("{courseId}")
    public R deleteCourse(@PathVariable String courseId){
        courseService.removeCourse(courseId);
        return R.ok();
    }
}

