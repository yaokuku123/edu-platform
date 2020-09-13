package com.yqj.serviceedu.service;

import com.yqj.serviceedu.entity.EduCourse;
import com.yqj.serviceedu.entity.EduTeacher;

import java.util.List;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: EduFrontService
 * Author: yaoqijun
 * Date: 2020/9/13 8:53
 */
public interface EduFrontService {
    //根据id获取前8条热门课程
    List<EduCourse> getHotCourse();

    //根据id获取前4个热门教师
    List<EduTeacher> getHotTeacher();
}
