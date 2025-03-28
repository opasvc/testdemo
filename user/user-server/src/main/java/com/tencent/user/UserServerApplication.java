package com.tencent.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@EnableAsync
@SpringBootApplication
public class UserServerApplication {
    public static void main(String[] args) {
        setProxy();
        SpringApplication.run(UserServerApplication.class);
        log.info("UserServerApplication 启动成功");
    }
    //设置系统代理

    private static void setProxy() {
        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "23345");
    }
}