package com.yqj.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: OssService
 * Author: yaoqijun
 * Date: 2020/8/29 20:24
 */
public interface OssService {
    //上传头像图片到oss
    String uploadAvatarFile(MultipartFile file);
}
