package com.yqj.serviceedu.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: ExcelListener
 * Author: yaoqijun
 * Date: 2020/8/31 18:57
 */
public class ExcelListener extends AnalysisEventListener<DemoStudent> {

    //读取除表头外的每行数据执行该方法
    @Override
    public void invoke(DemoStudent data, AnalysisContext context) {
        System.out.println(data);
    }

    //获取表头数据
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println(headMap);
    }

    //读取结束执行该方法
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("finish");
    }
}
