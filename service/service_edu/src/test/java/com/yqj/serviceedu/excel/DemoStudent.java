package com.yqj.serviceedu.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: DemoStudent
 * Author: yaoqijun
 * Date: 2020/8/31 18:30
 */
@Data
public class DemoStudent {
    @ExcelProperty(value = "学生编号",index = 0)
    private Integer no;
    @ExcelProperty(value = "学生姓名",index = 1)
    private String name;
}
