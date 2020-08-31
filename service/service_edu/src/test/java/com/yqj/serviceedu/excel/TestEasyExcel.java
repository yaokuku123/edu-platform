package com.yqj.serviceedu.excel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: TestEasyExcel
 * Author: yaoqijun
 * Date: 2020/8/31 18:30
 */
public class TestEasyExcel {

    //写文件
    @Test
    public void testEasyExcelWrite(){
        String filePath = "D:/student.xlsx";
        EasyExcel.write(filePath,DemoStudent.class).sheet("学生信息").doWrite(getData());
    }

    public static List<DemoStudent> getData(){
        List<DemoStudent> students = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoStudent student = new DemoStudent();
            student.setNo(i);
            student.setName("yorick"+i);
            students.add(student);
        }
        return students;
    }

    //读文件
    @Test
    public void testEasyExcelRead(){
        String filePath = "D:/student.xlsx";
        EasyExcel.read(filePath,DemoStudent.class,new ExcelListener()).sheet().doRead();
    }
}
