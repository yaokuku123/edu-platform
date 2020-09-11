package com.yqj.serviceedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: EduApplication
 * Author: yaoqijun
 * Date: 2020/8/26 11:10
 */
@SpringBootApplication
@ComponentScan("com.yqj")
@EnableDiscoveryClient //nacos注册
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
