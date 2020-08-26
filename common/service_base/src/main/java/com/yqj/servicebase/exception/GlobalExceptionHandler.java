package com.yqj.servicebase.exception;

import com.yqj.commonutils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: GlobalExceptionHandler
 * Author: yaoqijun
 * Date: 2020/8/26 14:21
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("全局处理异常");
    }
}
