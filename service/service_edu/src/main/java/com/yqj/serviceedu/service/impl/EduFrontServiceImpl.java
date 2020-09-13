package com.yqj.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yqj.serviceedu.entity.EduCourse;
import com.yqj.serviceedu.entity.EduTeacher;
import com.yqj.serviceedu.service.EduCourseService;
import com.yqj.serviceedu.service.EduFrontService;
import com.yqj.serviceedu.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: EduFrontServiceImpl
 * Author: yaoqijun
 * Date: 2020/9/13 8:54
 */
@Service
public class EduFrontServiceImpl implements EduFrontService {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduTeacherService teacherService;

    //根据id获取前8条热门课程
    @Cacheable(value = "hot",key = "'courseList'")
    @Override
    public List<EduCourse> getHotCourse() {
        QueryWrapper<EduCourse> courseWrapper = new QueryWrapper<>();
        courseWrapper.orderByDesc("id");
        courseWrapper.last("limit 8");
        List<EduCourse> courseList = courseService.list(courseWrapper);
        return courseList;
    }

    //根据id获取前4个热门教师
    @Cacheable(value = "hot",key = "'teacherList'")
    @Override
    public List<EduTeacher> getHotTeacher() {
        QueryWrapper<EduTeacher> teacherWrapper = new QueryWrapper<>();
        teacherWrapper.orderByDesc("id");
        teacherWrapper.last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(teacherWrapper);
        return teacherList;
    }
}
