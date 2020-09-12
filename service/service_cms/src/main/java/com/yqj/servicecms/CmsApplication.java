package com.yqj.servicecms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: CmsApplication
 * Author: yaoqijun
 * Date: 2020/9/12 14:18
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.yqj"})
@MapperScan("com.yqj.servicecms.mapper")
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class,args);
    }
}
