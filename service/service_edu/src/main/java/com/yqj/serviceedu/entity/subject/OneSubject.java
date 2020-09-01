package com.yqj.serviceedu.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: OneSubject
 * Author: yaoqijun
 * Date: 2020/9/1 9:36
 */
@Data
public class OneSubject {
    private String id;
    private String title;
    private List<TwoSubject> children = new ArrayList<>();
}
