package com.yqj.serviceedu.service;

import com.yqj.serviceedu.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author yqj
 * @since 2020-08-31
 */
public interface EduSubjectService extends IService<EduSubject> {

    //通过读取excel实现添加课程分类
    void addSubject(MultipartFile file);
}
