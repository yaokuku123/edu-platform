package com.yqj.sevicemsm.controller;

import com.yqj.commonutils.R;
import com.yqj.sevicemsm.service.MsmService;
import com.yqj.sevicemsm.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: MsmController
 * Author: yaoqijun
 * Date: 2020/9/13 12:28
 */
@RestController
@RequestMapping("/servicemsm/msm")
@CrossOrigin
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //通过阿里云短信服务发送验证码
    @GetMapping("/send/{phone}")
    public R send(@PathVariable String phone){
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)){
            return R.ok();
        }
        code = RandomUtil.getFourBitRandom();
        Map<String,Object> param = new HashMap<>();
        param.put("code",code);
        boolean isSuccess = msmService.send(param,phone);
        if (isSuccess){
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return R.ok();
        }else {
            return R.error();
        }
    }
}
