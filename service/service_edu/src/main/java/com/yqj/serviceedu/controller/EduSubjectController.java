package com.yqj.serviceedu.controller;


import com.yqj.commonutils.R;
import com.yqj.serviceedu.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author yqj
 * @since 2020-08-31
 */
@RestController
@RequestMapping("/serviceedu/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    //上传文件，通过读取excel实现添加课程分类
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        subjectService.addSubject(file);
        return R.ok();
    }
}

