package com.yqj.servicevod.controller;

import com.yqj.commonutils.R;
import com.yqj.servicevod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: VodController
 * Author: yaoqijun
 * Date: 2020/9/11 12:20
 */
@RestController
@RequestMapping("/servicevod/video")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    //上传视频到阿里云点播平台
    @PostMapping("uploadVideo")
    public R uploadVideo(MultipartFile file){
        String videoId = vodService.uploadVideo(file);
        return R.ok().data("videoId",videoId);
    }

    //根据id删除阿里云视频
    @DeleteMapping("deleteVideo/{id}")
    public R deleteVideo(@PathVariable String id){
        vodService.deleteVideo(id);
        return R.ok();
    }
}
