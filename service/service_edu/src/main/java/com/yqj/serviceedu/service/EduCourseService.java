package com.yqj.serviceedu.service;

import com.yqj.serviceedu.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yqj.serviceedu.entity.vo.CourseInfo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author yqj
 * @since 2020-09-01
 */
public interface EduCourseService extends IService<EduCourse> {
    //添加课程基本信息
    void addCourseInfo(CourseInfo courseInfo);
}
