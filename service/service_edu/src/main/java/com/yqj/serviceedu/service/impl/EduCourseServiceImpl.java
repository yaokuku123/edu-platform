package com.yqj.serviceedu.service.impl;

import com.yqj.servicebase.exception.MySystemException;
import com.yqj.serviceedu.entity.EduCourse;
import com.yqj.serviceedu.entity.EduCourseDescription;
import com.yqj.serviceedu.entity.vo.CourseInfo;
import com.yqj.serviceedu.mapper.EduCourseMapper;
import com.yqj.serviceedu.service.EduCourseDescriptionService;
import com.yqj.serviceedu.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author yqj
 * @since 2020-09-01
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    //注入课程描述的service
    @Autowired
    private EduCourseDescriptionService descriptionService;

    //添加课程基本信息
    @Override
    public void addCourseInfo(CourseInfo courseInfo) {
        //添加课程基本信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfo,eduCourse);
        int insert = baseMapper.insert(eduCourse);

        //判断插入数据是否成功
        if (insert == 0){
            throw new MySystemException(20001,"插入数据失败");
        }

        //添加课程描述信息
        //获取插入课程基本信息的id用于和描述信息做一对一绑定
        String cid = eduCourse.getId();
        //添加描述信息
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfo.getDescription());
        eduCourseDescription.setId(cid);
        descriptionService.save(eduCourseDescription);
    }
}
