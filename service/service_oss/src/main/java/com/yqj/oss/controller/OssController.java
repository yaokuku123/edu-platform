package com.yqj.oss.controller;

import com.yqj.commonutils.R;
import com.yqj.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: OssController
 * Author: yaoqijun
 * Date: 2020/8/29 20:24
 */
@RestController
@RequestMapping("/serviceoss/fileoss")
public class OssController {

    @Autowired
    private OssService ossService;

    //上传头像图片到oss
    @PostMapping
    public R uploadFile(MultipartFile file){
        String url = ossService.uploadAvatarFile(file);
        return R.ok().data("url",url);
    }
}
