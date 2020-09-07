package com.yqj.serviceedu.service;

import com.yqj.serviceedu.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author yqj
 * @since 2020-09-01
 */
public interface EduVideoService extends IService<EduVideo> {

    //删除小节信息
    void removeVideo(String courseId);
}
