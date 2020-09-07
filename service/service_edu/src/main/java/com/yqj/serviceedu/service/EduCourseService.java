package com.yqj.serviceedu.service;

import com.yqj.serviceedu.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yqj.serviceedu.entity.vo.CourseInfo;
import com.yqj.serviceedu.entity.vo.CoursePublishVo;

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
    String addCourseInfo(CourseInfo courseInfo);

    //根据id查询课程
    CourseInfo getCourseById(String courseId);

    //修改课程信息
    void updateCourse(CourseInfo courseInfo);

    //根据课程id查询课程确认信息
    CoursePublishVo publishCourseInfo(String id);
}
