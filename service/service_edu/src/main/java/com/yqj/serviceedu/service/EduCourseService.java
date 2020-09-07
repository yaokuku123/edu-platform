package com.yqj.serviceedu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yqj.serviceedu.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yqj.serviceedu.entity.vo.CourseInfo;
import com.yqj.serviceedu.entity.vo.CoursePublishVo;
import com.yqj.serviceedu.entity.vo.CourseQuery;

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

    //课程列表分页查询
    void pageQuery(Page<EduCourse> pageCourse, CourseQuery courseQuery);

    //根据课程id删除课程信息
    void removeCourse(String courseId);
}
