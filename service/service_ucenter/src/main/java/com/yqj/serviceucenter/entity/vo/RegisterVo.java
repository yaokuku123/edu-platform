package com.yqj.serviceucenter.entity.vo;

import lombok.Data;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: registerVo
 * Author: yaoqijun
 * Date: 2020/9/13 17:02
 */
@Data
public class RegisterVo {

    private String nickName;  //昵称
    private String mobile;  //手机号
    private String password; //密码
    private String code; //验证码
}
