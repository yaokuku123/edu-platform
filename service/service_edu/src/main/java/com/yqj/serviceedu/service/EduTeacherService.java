package com.yqj.serviceedu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yqj.serviceedu.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yqj.serviceedu.entity.vo.TeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author yqj
 * @since 2020-08-26
 */
public interface EduTeacherService extends IService<EduTeacher> {

    void pageQuery(Page<EduTeacher> page, TeacherQuery teacherQuery);
}
