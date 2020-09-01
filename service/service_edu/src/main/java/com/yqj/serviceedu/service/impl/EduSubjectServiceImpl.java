package com.yqj.serviceedu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yqj.serviceedu.entity.EduSubject;
import com.yqj.serviceedu.entity.excel.SubjectData;
import com.yqj.serviceedu.entity.subject.OneSubject;
import com.yqj.serviceedu.entity.subject.TwoSubject;
import com.yqj.serviceedu.listener.SubjectExcelListener;
import com.yqj.serviceedu.mapper.EduSubjectMapper;
import com.yqj.serviceedu.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

    //获取课程分类列表
    @Override
    public List<OneSubject> getSubjectList() {
        //查询一级课程分类
        QueryWrapper<EduSubject> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("parent_id","0");
        List<EduSubject> oneSubjects = baseMapper.selectList(wrapper1);
        //查询二级课程分类
        QueryWrapper<EduSubject> wrapper2 = new QueryWrapper<>();
        wrapper2.ne("parent_id","0");
        List<EduSubject> twoSubjects = baseMapper.selectList(wrapper2);

        //最终返回的数据集合
        List<OneSubject> finalList = new ArrayList<>();
        //封装数据
        for (int i = 0; i < oneSubjects.size(); i++) {
            //封装一级分类的id和title属性
            EduSubject oneEduSubject = oneSubjects.get(i);
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(oneEduSubject,oneSubject);
            //封装一级分类的children集合数据属性
            //当前一级分类下面的二级分类数组
            List<TwoSubject> twoSubEduSubjects = new ArrayList<>();
            for (int j = 0; j < twoSubjects.size(); j++) {
                EduSubject twoEduSubject = twoSubjects.get(j);
                if (twoEduSubject.getParentId().equals(oneEduSubject.getId())){
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(twoEduSubject,twoSubject);
                    twoSubEduSubjects.add(twoSubject);
                }
            }
            oneSubject.setChildren(twoSubEduSubjects);
            //添加到返回的数据集合中
            finalList.add(oneSubject);
        }

        return finalList;
    }
}
