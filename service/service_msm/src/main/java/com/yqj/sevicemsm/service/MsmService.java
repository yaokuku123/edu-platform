package com.yqj.sevicemsm.service;

import java.util.Map;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: MsmService
 * Author: yaoqijun
 * Date: 2020/9/13 12:29
 */
public interface MsmService {
    //通过阿里云短信服务发送验证码
    boolean send(Map<String, Object> param, String phone);
}
