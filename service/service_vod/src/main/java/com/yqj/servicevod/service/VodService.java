package com.yqj.servicevod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: VodService
 * Author: yaoqijun
 * Date: 2020/9/11 12:21
 */
public interface VodService {
    //上传视频到阿里云点播平台
    String uploadVideo(MultipartFile file);

    //根据id删除阿里云视频
    void deleteVideo(String id);
}
