package com.tencent.user.utlis;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.lang.generator.SnowflakeGenerator;

public class EntryId {
    public static Long getId() {
        //mybatis plus雪花算法生成id
        //return SnowflakeIdWorker.getId();
        SnowflakeGenerator generator = new SnowflakeGenerator();
        return generator.next();
    }

    public static void main(String[] args) {
        System.out.println(getId());
    }
}
