package com.tencent.user.controller;

import com.tencent.message.client.feign.MessageControllerFeign;
import com.tencent.user.domain.VO.Result;
import com.tencent.user.domain.entity.UserInfo;
import com.tencent.user.service.UserInfoService;
import com.tencent.user.utlis.ImageCodeUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;
    @Resource

    private MessageControllerFeign messageControllerFeign;

    @GetMapping("/image")
    public String image(@Param("deviceId") String deviceId) {
        log.info("设备ID为:{},开始获取图像...", deviceId);
        String imageCode = ImageCodeUtil.getImageCode();

        return imageCode;
    }

    @GetMapping("/list")
    public Result test() {
        log.info("设备ID为:{},开始获取图像...");
        messageControllerFeign.testSendMessage();
//        System.exit(1);
        return Result.success(userInfoService.list());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public Result register(@RequestBody UserInfo po) {
        log.info("注册信息为:{}", po);
//  TODO 实现注册接口

        return Result.success(po);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public Result login(@RequestBody Map map) {
        log.info("登录信息为:{}", map);
//      TODO 实现登录接口
        return Result.success("登录成功", map);
    }

    /**
     * 退出登录
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/logout")
    public Result logout() {
        log.info("登出信息为:{}");
//        TODO 实现登出接口
        return Result.success("登出成功");
    }
}
