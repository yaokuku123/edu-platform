package com.yqj.serviceedu.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: SubjectData
 * Author: yaoqijun
 * Date: 2020/8/31 19:54
 */
@Data
public class SubjectData {
    @ExcelProperty(index = 0)
    private String oneSubjectName;
    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
