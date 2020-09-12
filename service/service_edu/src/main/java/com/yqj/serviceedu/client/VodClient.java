package com.yqj.serviceedu.client;

import com.yqj.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: VodClient
 * Author: yaoqijun
 * Date: 2020/9/12 7:42
 */
@FeignClient("service-vod")
@Component
public interface VodClient {

    //根据id删除阿里云视频
    @DeleteMapping("/servicevod/video/deleteVideo/{id}")
    public R deleteVideo(@PathVariable("id") String id);

    //根据多个id批量删除阿里云视频
    @DeleteMapping("/servicevod/video/delete-batch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);
}
