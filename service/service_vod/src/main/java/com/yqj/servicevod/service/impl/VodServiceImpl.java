package com.yqj.servicevod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.yqj.servicebase.exception.MySystemException;
import com.yqj.servicevod.service.VodService;
import com.yqj.servicevod.utils.InitVodClient;
import com.yqj.servicevod.utils.VodPropertiesUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: VodServiceImpl
 * Author: yaoqijun
 * Date: 2020/9/11 12:21
 */
@Service
public class VodServiceImpl implements VodService {

    //上传视频到阿里云点播平台，返回视频的id
    @Override
    public String uploadVideo(MultipartFile file) {
        try {
            //获取上传文件的原始名称
            String fileName = file.getOriginalFilename();
            //获取去掉结尾后缀的名称
            String title = fileName.substring(0,fileName.lastIndexOf("."));
            //文件输入流
            InputStream inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(VodPropertiesUtils.KEY_ID, VodPropertiesUtils.KEY_SECRET,
                    title, fileName, inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            String videoId = null;
            if(response.isSuccess()){
                videoId = response.getVideoId();
            } else {
                videoId = response.getVideoId();
            }
            return videoId;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //根据id删除阿里云视频
    @Override
    public void deleteVideo(String id) {
        try {
            DefaultAcsClient client = InitVodClient.initVodClient(VodPropertiesUtils.KEY_ID, VodPropertiesUtils.KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(id);
            client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
            throw new MySystemException(20001,"删除视频失败");
        }
    }
}
