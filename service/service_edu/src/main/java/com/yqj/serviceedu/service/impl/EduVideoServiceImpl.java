package com.yqj.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yqj.serviceedu.client.VodClient;
import com.yqj.serviceedu.entity.EduVideo;
import com.yqj.serviceedu.mapper.EduVideoMapper;
import com.yqj.serviceedu.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author yqj
 * @since 2020-09-01
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;

    //删除小节信息
    @Override
    public void removeVideo(String courseId) {
        //删除视频信息
        //查询小节列表
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        wrapperVideo.select("video_source_id");
        List<EduVideo> eduVideoList = baseMapper.selectList(wrapperVideo);
        //将EduVideo中的视频id取出
        List<String> videoIds = new ArrayList<>();
        for (EduVideo eduVideo : eduVideoList) {
            String videoSourceId = eduVideo.getVideoSourceId();
            if (!StringUtils.isEmpty(videoSourceId)){
                videoIds.add(videoSourceId);
            }
        }
        //远程调用删除视频
        if (videoIds.size()>0){
            vodClient.deleteBatch(videoIds);
        }

        //删除小节信息
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }
}
