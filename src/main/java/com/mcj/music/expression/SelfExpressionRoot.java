package com.mcj.music.expression;

import com.mcj.music.domain.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author mcj
 * @date 2022/11/20 14:49
 * @email 2037612492@qq.com
 * @description 自定义权限校验规则
 */
@Component("ex")
public class SelfExpressionRoot {

    public boolean hasAuthority(String authority) {
        // 获取当前用户的权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        List<String> permissions = loginUser.getPermissions();
        return permissions.contains(authority);

    }

}
