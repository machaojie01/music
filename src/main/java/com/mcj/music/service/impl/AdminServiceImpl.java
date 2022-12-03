package com.mcj.music.service.impl;

import com.mcj.music.domain.LoginUser;
import com.mcj.music.service.AdminService;
import com.mcj.music.utils.JwtUtil;
import com.mcj.music.utils.RedisCache;
import com.mcj.music.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author mcj
 * @date 2022/10/9 10:27
 * @email 2037612492@qq.com
 * @description 管理员service实现类
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    /**
     * 验证用户名密码是否正确
     *
     * @param username 用户名
     * @param password 密码
     */
    @Override
    public Map<String, String> verifyPassword(String username, String password) {
        // AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 如果认证没通过，给出对应的提示
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登陆失败");
        }
        // 如果认证通过了，使用userId生成一个jwt
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String id = loginUser.getAdmin().getId().toString();
        String jwt = JwtUtil.createJWT(id);
        Map<String, String> result = new HashMap<>();
        result.put("token", jwt);
        // 把完整的用户信息存入redis userId作为key
        redisCache.setCacheObject("token:" + id, loginUser);
        return result;
    }

    /**
     * 退出登录
     * @return
     */
    @Override
    public ResponseUtils logout() {

        // 获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authenticationToken = ((UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication());
        LoginUser loginUser = (LoginUser) authenticationToken.getPrincipal();
        String id = loginUser.getAdmin().getId().toString();
        // 删除redis中的值
        String redisKey = "token:" + id;
        redisCache.deleteObject(redisKey);
        return ResponseUtils.success("注销成功");
    }
}
