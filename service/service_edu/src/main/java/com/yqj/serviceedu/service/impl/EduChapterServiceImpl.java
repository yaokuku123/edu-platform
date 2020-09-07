package com.yqj.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yqj.servicebase.exception.MySystemException;
import com.yqj.serviceedu.entity.EduChapter;
import com.yqj.serviceedu.entity.EduVideo;
import com.yqj.serviceedu.entity.chapter.ChapterVo;
import com.yqj.serviceedu.entity.chapter.VideoVo;
import com.yqj.serviceedu.mapper.EduChapterMapper;
import com.yqj.serviceedu.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqj.serviceedu.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author yqj
 * @since 2020-09-01
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService videoService;

    //获得章节和小节的全部数据
    @Override
    public List<ChapterVo> getChapterVideo(String courseId) {
        //查询章节数据
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id",courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(wrapperChapter);
        //查询小节数据
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperChapter.eq("course_id",courseId);
        List<EduVideo> eduVideoList = videoService.list(wrapperVideo);
        //封装数据
        List<ChapterVo> finalList = new ArrayList<>();
        for (int i = 0; i < eduChapterList.size(); i++) {
            EduChapter eduChapter = eduChapterList.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);

            List<VideoVo> subVideoList = new ArrayList<>();
            for (int j = 0; j < eduVideoList.size(); j++) {
                EduVideo eduVideo = eduVideoList.get(j);
                if (eduVideo.getChapterId().equals(eduChapter.getId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    subVideoList.add(videoVo);
                }
            }
            chapterVo.setChildren(subVideoList);
            finalList.add(chapterVo);
        }
        return finalList;
    }

    //若小节数据为空则删除章节数据
    @Override
    public boolean deleteChapter(String chapterId) {
        //根据章节id查询小节信息
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("chapter_id",chapterId);
        int count = videoService.count(wrapperVideo);
        if (count == 0){
            //当前章节没有小节，可以删除
            int result = baseMapper.deleteById(chapterId);
            return result>0;
        }else {
            //当前章节有小节，不能删除
            throw new MySystemException(20001,"有小节数据不能删除");
        }
    }

    //删除章节信息
    @Override
    public void removeChapter(String courseId) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }
}
