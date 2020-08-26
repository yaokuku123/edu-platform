package com.yqj.servicebase.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: MySystemException
 * Author: yaoqijun
 * Date: 2020/8/26 16:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MySystemException extends RuntimeException {
    private Integer code;
    private String msg;
}
