package com.yqj.serviceedu.controller;


import com.yqj.commonutils.R;
import com.yqj.serviceedu.entity.subject.OneSubject;
import com.yqj.serviceedu.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    //获取课程分类列表
    @GetMapping("getSubjectList")
    public R getSubjectList(){
        List<OneSubject> list = subjectService.getSubjectList();
        return R.ok().data("list",list);
    }
}

