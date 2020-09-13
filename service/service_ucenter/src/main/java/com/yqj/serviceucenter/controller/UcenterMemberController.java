package com.yqj.serviceucenter.controller;


import com.yqj.commonutils.R;
import com.yqj.serviceucenter.entity.UcenterMember;
import com.yqj.serviceucenter.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author yqj
 * @since 2020-09-13
 */
@RestController
@RequestMapping("/serviceucenter/member")
@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    //登录
    @PostMapping("login")
    public R loginUser(@RequestBody UcenterMember member){
        String token = memberService.login(member);
        return R.ok().data("token",token);
    }
}

