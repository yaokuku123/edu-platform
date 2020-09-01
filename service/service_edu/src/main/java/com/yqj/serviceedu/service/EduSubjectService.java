package com.yqj.serviceedu.service;

import com.yqj.serviceedu.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yqj.serviceedu.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    //获取课程分类列表
    List<OneSubject> getSubjectList();
}
