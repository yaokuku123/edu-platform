package com.yqj.servicevod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: VodePropertiesUtils
 * Author: yaoqijun
 * Date: 2020/9/11 12:24
 */
@Component
public class VodPropertiesUtils implements InitializingBean {

    @Value("${aliyun.vod.file.keyid}")
    private String keyId;
    @Value("${aliyun.vod.file.keysecret}")
    private String keySecret;

    public static String KEY_ID;
    public static String KEY_SECRET;

    @Override
    public void afterPropertiesSet() throws Exception {
        KEY_ID = keyId;
        KEY_SECRET = keySecret;
    }
}
