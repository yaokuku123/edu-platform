package com.yqj.serviceucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: UcenterApplication
 * Author: yaoqijun
 * Date: 2020/9/13 16:19
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.yqj"})
@MapperScan("com.yqj.serviceucenter.mapper")
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class,args);
    }
}
