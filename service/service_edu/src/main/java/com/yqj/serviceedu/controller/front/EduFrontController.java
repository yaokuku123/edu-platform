package com.yqj.serviceedu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yqj.commonutils.R;
import com.yqj.serviceedu.entity.EduCourse;
import com.yqj.serviceedu.entity.EduTeacher;
import com.yqj.serviceedu.service.EduCourseService;
import com.yqj.serviceedu.service.EduFrontService;
import com.yqj.serviceedu.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: EduFrontController
 * Author: yaoqijun
 * Date: 2020/9/12 15:09
 */
@RestController
@RequestMapping("/serviceedu/front")
@CrossOrigin
public class EduFrontController {

    @Autowired
    private EduFrontService frontService;

    //获取前8条热门课程和前4个热门教师
    @GetMapping("index")
    public R getHotCourseAndTeacher(){
        //根据id获取前8条热门课程
        List<EduCourse> courseList = frontService.getHotCourse();
        //根据id获取前4个热门教师
        List<EduTeacher> teacherList = frontService.getHotTeacher();
        return R.ok().data("courseList",courseList).data("teacherList",teacherList);
    }
}
