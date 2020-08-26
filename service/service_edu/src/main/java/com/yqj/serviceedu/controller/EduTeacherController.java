package com.yqj.serviceedu.controller;


import com.yqj.serviceedu.entity.EduTeacher;
import com.yqj.serviceedu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author yqj
 * @since 2020-08-26
 */
@Api(description="讲师管理")
@RestController
@RequestMapping("/serviceedu/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    //查询所有讲师
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public List<EduTeacher> findAll(){
        List<EduTeacher> list = teacherService.list(null);
        return list;
    }

    //删除指定id的讲师
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public boolean deleteTeacher(@ApiParam(name = "id", value = "讲师ID", required = true)
                                     @PathVariable String id){
        boolean flag = teacherService.removeById(id);
        return flag;
    }

}

