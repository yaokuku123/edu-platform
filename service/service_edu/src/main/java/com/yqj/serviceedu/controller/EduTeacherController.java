package com.yqj.serviceedu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yqj.commonutils.R;
import com.yqj.servicebase.exception.MySystemException;
import com.yqj.serviceedu.entity.EduTeacher;
import com.yqj.serviceedu.entity.vo.TeacherQuery;
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
@CrossOrigin //跨域问题
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    //查询所有讲师
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R findAll(){
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items",list);
    }

    //删除指定id的讲师
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public R deleteTeacher(@ApiParam(name = "id", value = "讲师ID", required = true)
                                     @PathVariable String id){
        boolean flag = teacherService.removeById(id);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //分页查询讲师记录
    @ApiOperation(value = "分页讲师列表")
    @PostMapping("pageTeacher/{current}/{limit}")
    public R pageTeacher(@ApiParam(name = "current", value = "当前页码", required = true)
                            @PathVariable long current,
                         @ApiParam(name = "limit", value = "每页记录数", required = true)
                            @PathVariable long limit,
                         @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
                            @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        teacherService.pageQuery(pageTeacher,teacherQuery);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }

    //新增讲师
    @ApiOperation(value = "新增讲师")
    @PostMapping("addTeacher")
    public R addTeacher(@ApiParam(name = "teacher", value = "讲师对象", required = true)
                            @RequestBody EduTeacher teacher){
        boolean save = teacherService.save(teacher);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //根据id查询讲师
    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("{id}")
    public R getTeacher(@ApiParam(name = "id", value = "讲师ID", required = true)
                            @PathVariable String id){
        EduTeacher teacher = teacherService.getById(id);
        return R.ok().data("teacher",teacher);
    }

    //根据id更新讲师
    @ApiOperation(value = "根据ID修改讲师")
    @PutMapping("{id}")
    public R updateTeacher(@ApiParam(name = "id", value = "讲师ID", required = true)
                               @PathVariable String id,
                           @ApiParam(name = "teacher", value = "讲师对象", required = true)
                                @RequestBody EduTeacher teacher){
        teacher.setId(id);
        boolean update = teacherService.updateById(teacher);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

}

