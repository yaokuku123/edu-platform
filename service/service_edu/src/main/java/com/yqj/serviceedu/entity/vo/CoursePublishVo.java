package com.yqj.serviceedu.entity.vo;

import lombok.Data;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: CoursePublishVo
 * Author: yaoqijun
 * Date: 2020/9/7 9:24
 */
@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
