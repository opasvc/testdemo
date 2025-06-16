package com.tencent.user.service;

import com.tencent.user.domain.entity.CustomUserDetails;
import com.tencent.user.domain.entity.UserInfo;
import com.tencent.user.mapper.UserInfoMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 自定义的 UserDetailsService 实现类
 * 用于根据用户名加载用户信息
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserInfoMapper userInfoMapper;

    /**
     * 构造函数
     * @param userInfoMapper 用户信息 Mapper
     */
    public CustomUserDetailsService(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    /**
     * 根据用户名加载用户信息
     * @param username 用户名
     * @return 用户详情
     * @throws UsernameNotFoundException 用户不存在时抛出
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoMapper.selectByUsername(username);
        if (userInfo == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new CustomUserDetails(userInfo);
    }
} 