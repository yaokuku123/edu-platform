package com.yqj.serviceedu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.yqj.serviceedu.entity.EduSubject;
import com.yqj.serviceedu.entity.excel.SubjectData;
import com.yqj.serviceedu.listener.SubjectExcelListener;
import com.yqj.serviceedu.mapper.EduSubjectMapper;
import com.yqj.serviceedu.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author yqj
 * @since 2020-08-31
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //通过读取excel实现添加课程分类
    @Override
    public void addSubject(MultipartFile file) {
        try{
            //使用easyExcel处理文件
            InputStream in = file.getInputStream();
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(this)).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
