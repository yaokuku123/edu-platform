package com.yqj.serviceedu.client.impl;

import com.yqj.commonutils.R;
import com.yqj.serviceedu.client.VodClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: VodClientDradeImpl
 * Author: yaoqijun
 * Date: 2020/9/12 9:57
 */
@Component
public class VodClientDradeImpl implements VodClient {
    @Override
    public R deleteVideo(String id) {
        return R.error().message("deleteVideo time out,Hystrix");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.error().message("deleteBatch time out,Hystrix");
    }
}
