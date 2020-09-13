package com.yqj.serviceucenter.controller;


import com.yqj.commonutils.JwtUtils;
import com.yqj.commonutils.R;
import com.yqj.serviceucenter.entity.UcenterMember;
import com.yqj.serviceucenter.entity.vo.RegisterVo;
import com.yqj.serviceucenter.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    //注册
    @PostMapping("register")
    public R registerUser(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok();
    }

    //根据token获取用户数据
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember member = memberService.getById(memberId);
        return R.ok().data("userInfo",member);
    }
}

