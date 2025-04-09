package com.tencent.user.domain.PO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
public class UserInfoPO {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;
    /**
     * 验证码
     */
    private String captcha;
    /**
     * 用户设备ID
     **/
    private String deviceId;
}
