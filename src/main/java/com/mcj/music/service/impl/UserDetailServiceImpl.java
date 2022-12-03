package com.mcj.music.service.impl;

import com.mcj.music.dao.AdminMapper;
import com.mcj.music.dao.MenuMapper;
import com.mcj.music.domain.Admin;
import com.mcj.music.domain.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author mcj
 * @date 2022/11/20 9:51
 * @email 2037612492@qq.com
 * @description 登陆查询用户信息实现类
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 查询用户信息
        Admin admin = adminMapper.queryUserByUsername(username);
        // 如果没有查询到用户抛异常
        if (Objects.isNull(admin)) {
            throw new RuntimeException("用户名或密码错误");
        }

        // 查询对应的权限信息
        List<String> list = menuMapper.selectPermsByUserId(admin.getId());


        // 把数据封装成UserDetails
        return new LoginUser(admin, list);
    }
}
