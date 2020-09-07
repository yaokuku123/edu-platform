package com.yqj.serviceedu.mapper;

import com.yqj.serviceedu.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yqj.serviceedu.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author yqj
 * @since 2020-09-01
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    //获取展示数据
    public CoursePublishVo getCoursePublishInfo(String courseId);
}
