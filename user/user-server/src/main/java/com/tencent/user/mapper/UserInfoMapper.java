package com.tencent.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.user.domain.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    @Select("SELECT * FROM user_info WHERE username = #{username}")
    UserInfo selectByUsername(@Param("username") String username);
}
