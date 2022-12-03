package com.mcj.music.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mcj
 * @date 2022/11/20 9:59
 * @email 2037612492@qq.com
 * @description 用登陆相关的实体
 */
@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {

    private Admin admin;

    /**
     * 存储权限信息
     */
    private List<String> permissions;

    /**
     * 存储spring Security需要的权限列表，不需要其序列化
     */
    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;

    public LoginUser (Admin admin, List<String> permissions) {
        this.admin = admin;
        this.permissions = permissions;
    }

    /**
     * 权限列表
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities != null) {
            return authorities;
        }
        // 把permissions中String类型的权限信息封装为SimpleGrantedAuthority
        authorities = permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return authorities;
    }

    /**
     * 密码
     * @return
     */
    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    /**
     * 用户名
     * @return
     */
    @Override
    public String getUsername() {
        return admin.getName();
    }

    /**
     * 判断账号是否过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账号是否没有锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 是否没有超时
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
