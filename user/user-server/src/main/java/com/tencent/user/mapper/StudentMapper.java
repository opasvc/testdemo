package com.tencent.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.user.entity.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {
}
