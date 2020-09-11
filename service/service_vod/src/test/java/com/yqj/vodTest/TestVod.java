package com.yqj.vodTest;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.*;
import org.junit.Test;

import java.util.List;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: TestVod
 * Author: yaoqijun
 * Date: 2020/9/11 10:20
 */
public class TestVod {

    //获取上传视频的url地址
    @Test
    public void testGetVodUpload() throws ClientException {
        //获取client对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI4G4tV9M3V2S6yQr1Xs9n", "lHFxm3UjJT75cZ33aLQxgdjzluRIFh");
        //获取request和response
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        //设置视频id
        request.setVideoId("f338e78033e749f5ae601a7326a6330a");
        //获取响应结果
        response = client.getAcsResponse(request);
        //播放地址
        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.println("url: " + playInfo.getPlayURL());
        }
        System.out.println("title: " + response.getVideoBase().getTitle());
    }

    //获取视频播放凭证
    @Test
    public void testVodAuth() throws ClientException {
        DefaultAcsClient client = InitObject.initVodClient("LTAI4G4tV9M3V2S6yQr1Xs9n", "lHFxm3UjJT75cZ33aLQxgdjzluRIFh");
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        request.setVideoId("f338e78033e749f5ae601a7326a6330a");
        response = client.getAcsResponse(request);
        System.out.println("auth: " + response.getPlayAuth());
    }

    //上传视频
    @Test
    public void testUpload() {
        String accessKeyId = "LTAI4G4tV9M3V2S6yQr1Xs9n";
        String accessKeySecret = "lHFxm3UjJT75cZ33aLQxgdjzluRIFh";
        //阿里云平台存储视频的名称
        String title = "testUploadFile";
        //视频路径
        String fileName = "D:\\study\\code\\项目资料\\在线教育平台项目\\项目资料\\1-阿里云上传测试视频\\6 - What If I Want to Move Faster.mp4";
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId,accessKeySecret,title,fileName);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        //判断是否有返回值，与上传成功与否无关
        if (response.isSuccess()){
            System.out.println("videoId: " + response.getVideoId());
        } else {
            System.out.println("videoId: " + response.getVideoId());
        }
    }
}
