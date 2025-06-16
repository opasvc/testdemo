package com.tencent.user.domain.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * 自定义的 UserDetails 实现类
 * 用于将 UserInfo 实体转换为 Spring Security 所需的 UserDetails 对象
 */
public class CustomUserDetails implements UserDetails {
    private final UserInfo userInfo;

    /**
     * 构造函数
     * @param userInfo 用户信息实体
     */
    public CustomUserDetails(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    /**
     * 获取用户权限
     * 目前所有用户都只有 ROLE_USER 权限
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    /**
     * 获取用户密码
     */
    @Override
    public String getPassword() {
        return userInfo.getPassword();
    }

    /**
     * 获取用户名
     */
    @Override
    public String getUsername() {
        return userInfo.getUsername();
    }

    /**
     * 账户是否未过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户是否未锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 凭证是否未过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账户是否启用
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
} 