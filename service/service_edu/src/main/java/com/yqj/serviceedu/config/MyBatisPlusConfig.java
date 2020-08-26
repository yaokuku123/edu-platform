package com.yqj.serviceedu.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: MyBatisPlusConfig
 * Author: yaoqijun
 * Date: 2020/8/26 11:11
 */
@Configuration
@MapperScan("com.yqj.serviceedu.mapper")
public class MyBatisPlusConfig {

    //逻辑删除
    @Bean
    public ISqlInjector iSqlInjector(){
        return new LogicSqlInjector();
    }

    //分页查询
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
