package com.tencent.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.user.domain.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
