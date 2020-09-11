package com.yqj.servicevod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: VodApplication
 * Author: yaoqijun
 * Date: 2020/9/11 9:55
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.yqj"})
@EnableDiscoveryClient //nacos注册
public class VodApplication {
    public static void main(String[] args) {
        SpringApplication.run(VodApplication.class,args);
    }
}
