package com.yqj.servicecms.controller;


import com.yqj.commonutils.R;
import com.yqj.servicecms.entity.CrmBanner;
import com.yqj.servicecms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author yqj
 * @since 2020-09-12
 */
@RestController
@RequestMapping("/servicecms/banner")
@CrossOrigin
public class CrmBannerController {

    @Autowired
    private CrmBannerService bannerService;

    //查询所有banner
    @GetMapping("getAllBanner")
    public R getAllBanner(){
        List<CrmBanner> list = bannerService.getAllBanner();
        return R.ok().data("list",list);
    }
}

