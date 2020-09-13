package com.yqj.serviceucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yqj.commonutils.JwtUtils;
import com.yqj.commonutils.MD5;
import com.yqj.servicebase.exception.MySystemException;
import com.yqj.serviceucenter.entity.UcenterMember;
import com.yqj.serviceucenter.mapper.UcenterMemberMapper;
import com.yqj.serviceucenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author yqj
 * @since 2020-09-13
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    //登录
    @Override
    public String login(UcenterMember member) {

        //获取手机号和密码
        String mobile = member.getMobile();
        String password = member.getPassword();

        //判断传入的手机或密码是否为空
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
            throw new MySystemException(20001,"登录失败，手机号或密码为空");
        }

        //查询数据库，看是否存储对应手机的数据
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        UcenterMember ucenterMember = baseMapper.selectOne(wrapper);
        if (ucenterMember == null){
            throw new MySystemException(20001,"登录失败，数据库未存储");
        }

        //判断密码是否正确
        if (!MD5.encrypt(password).equals(ucenterMember.getPassword())){
            throw new MySystemException(20001,"登录失败，密码不正确");
        }

        //账号是否可用
        if (ucenterMember.getIsDisabled()){
            throw new MySystemException(20001,"登录失败，账号不可用");
        }

        String token = JwtUtils.getJwtToken(ucenterMember.getId(),ucenterMember.getNickname());
        return token;
    }
}
