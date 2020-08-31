package com.yqj.serviceedu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yqj.servicebase.exception.MySystemException;
import com.yqj.serviceedu.entity.EduSubject;
import com.yqj.serviceedu.entity.excel.SubjectData;
import com.yqj.serviceedu.service.EduSubjectService;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: SubjectExcelListener
 * Author: yaoqijun
 * Date: 2020/8/31 19:56
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    //该监听器为new出来的，不在spring的管理中。需要手动传入service用于操作数据库
    private EduSubjectService subjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(SubjectData data, AnalysisContext context) {
        //判断文件是否为空
        if (data == null){
            throw new MySystemException(20001,"文件为空");
        }

        //存入数据库一级分类
        EduSubject oneSubject = this.existOneSubject(data.getOneSubjectName());
        //不存在该一级分类字段，执行添加操作
        if (oneSubject == null){
            oneSubject = new EduSubject();
            oneSubject.setTitle(data.getOneSubjectName());
            oneSubject.setParentId("0");
            subjectService.save(oneSubject);
        }

        //获取一级分类的id
        String parentId = oneSubject.getId();
        //存入数据库二级分类
        EduSubject twoSubject = this.existTwoSubject(data.getTwoSubjectName(),parentId);
        //不存在该二级分类字段，执行添加操作
        if (twoSubject == null){
            twoSubject = new EduSubject();
            twoSubject.setTitle(data.getTwoSubjectName());
            twoSubject.setParentId(parentId);
            subjectService.save(twoSubject);
        }
    }

    //查找数据库是否已经存储该一级分类字段
    private EduSubject existOneSubject(String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }

    //查找数据库是否已经存储该二级分类字段
    private EduSubject existTwoSubject(String name,String parentId){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",parentId);
        EduSubject twoSubject = subjectService.getOne(wrapper);
        return twoSubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
