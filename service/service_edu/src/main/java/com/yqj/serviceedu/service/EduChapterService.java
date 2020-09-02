package com.yqj.serviceedu.service;

import com.yqj.serviceedu.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yqj.serviceedu.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author yqj
 * @since 2020-09-01
 */
public interface EduChapterService extends IService<EduChapter> {
    //获得章节和小节的全部数据
    List<ChapterVo> getChapterVideo(String courseId);
}
