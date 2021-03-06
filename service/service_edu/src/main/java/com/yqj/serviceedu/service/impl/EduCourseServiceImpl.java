package com.yqj.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yqj.servicebase.exception.MySystemException;
import com.yqj.serviceedu.entity.EduCourse;
import com.yqj.serviceedu.entity.EduCourseDescription;
import com.yqj.serviceedu.entity.vo.CourseInfo;
import com.yqj.serviceedu.entity.vo.CoursePublishVo;
import com.yqj.serviceedu.entity.vo.CourseQuery;
import com.yqj.serviceedu.mapper.EduCourseMapper;
import com.yqj.serviceedu.service.EduChapterService;
import com.yqj.serviceedu.service.EduCourseDescriptionService;
import com.yqj.serviceedu.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqj.serviceedu.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Autowired
    private EduVideoService videoService;

    @Autowired
    private EduChapterService chapterService;

    //添加课程基本信息
    @Override
    public String addCourseInfo(CourseInfo courseInfo) {
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

        return cid;
    }

    //根据id查询课程
    @Override
    public CourseInfo getCourseById(String courseId) {
        CourseInfo courseInfo = new CourseInfo();
        //查询课程基本信息
        EduCourse eduCourse = baseMapper.selectById(courseId);
        BeanUtils.copyProperties(eduCourse,courseInfo);
        //查询课程描述信息
        EduCourseDescription eduCourseDescription = descriptionService.getById(courseId);
        courseInfo.setDescription(eduCourseDescription.getDescription());
        return courseInfo;
    }

    //修改课程信息
    @Override
    public void updateCourse(CourseInfo courseInfo) {
        //修改课程基本信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfo,eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if (update==0){
            throw new MySystemException(20001,"修改课程失败");
        }
        //修改课程描述信息
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfo,eduCourseDescription);
        descriptionService.updateById(eduCourseDescription);
    }

    //根据课程id查询课程确认信息
    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        CoursePublishVo coursePublishInfo = baseMapper.getCoursePublishInfo(id);
        return coursePublishInfo;
    }

    @Override
    public void pageQuery(Page<EduCourse> pageCourse, CourseQuery courseQuery) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();

        wrapper.orderByDesc("gmt_create");
        if(courseQuery==null){
            baseMapper.selectPage(pageCourse,wrapper);
            return;
        }
        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();
        if(!StringUtils.isEmpty(title)){
            wrapper.like("title",title);
        }
        if(!StringUtils.isEmpty(status)){
            wrapper.eq("status",status);
        }
        baseMapper.selectPage(pageCourse,wrapper);
    }

    //根据课程id删除课程信息
    @Override
    public void removeCourse(String courseId) {
        //删除小节信息
        videoService.removeVideo(courseId);
        //删除章节信息
        chapterService.removeChapter(courseId);
        //删除课程描述信息
        descriptionService.removeById(courseId);
        //删除课程基本信息
        baseMapper.deleteById(courseId);
    }
}
