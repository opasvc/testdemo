package com.tencent.user.controller;

import com.tencent.message.client.feign.MessageControllerFeign;
import com.tencent.user.domain.VO.Result;
import com.tencent.user.domain.entity.UserInfo;
import com.tencent.user.service.UserInfoService;
import com.tencent.user.utlis.EntryId;
import com.tencent.user.utlis.ImageCodeUtil;
import com.tencent.user.utlis.JwtUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 用户信息控制器
 * 处理用户相关的请求，包括注册、登录、登出等
 */
@Slf4j
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;
    @Resource
    private MessageControllerFeign messageControllerFeign;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private JwtUtil jwtUtil;

    /**
     * 获取验证码图片
     * @param deviceId 设备ID
     * @return 验证码图片的 Base64 编码
     */
    @GetMapping("/image")
    public String image(@Param("deviceId") String deviceId) {
        log.info("设备ID为:{},开始获取图像...", deviceId);
        String imageCode = ImageCodeUtil.getImageCode();
        return imageCode;
    }

    /**
     * 获取用户列表
     * @return 用户列表
     */
    @GetMapping("/list")
    public Result test() {
        log.info("设备ID为:{},开始获取图像...");
        messageControllerFeign.testSendMessage();
        return Result.success(userInfoService.list());
    }

    /**
     * 用户注册
     * @param po 用户信息
     * @return 注册结果
     */
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public Result register(@RequestBody UserInfo po) {
        log.info("注册信息为:{}", po);
        // 检查用户名是否已存在
        if (userInfoService.getByUsername(po.getUsername()) != null) {
            return Result.error("用户名已存在");
        }
        
        // 加密密码
        po.setPassword(passwordEncoder.encode(po.getPassword()));
        po.setId(EntryId.getId());
        // 保存用户信息
        userInfoService.save(po);
        return Result.success("注册成功");
    }

    /**
     * 用户登录
     * @param map 包含用户名和密码的 Map
     * @return 登录结果和 JWT token
     */
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public Result login(@RequestBody Map<String, String> map) {
        log.info("登录信息为:{}", map);
        String username = map.get("username");
        String password = map.get("password");
        
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            
            String token = jwtUtil.generateToken(userDetails);
            return Result.success("登录成功", token);
        } catch (Exception e) {
            return Result.error("用户名或密码错误");
        }
    }

    /**
     * 用户登出
     * @return 登出结果
     */
    @RequestMapping(method = RequestMethod.POST, value = "/logout")
    public Result logout() {
        log.info("登出信息为:{}");
        SecurityContextHolder.clearContext();
        return Result.success("登出成功");
    }
}
