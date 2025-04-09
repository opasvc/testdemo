package com.tencent.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class MessageServerApplication {
    static {
        log.info("设置系统代理");
        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "23345");
    }

    public static void main(String[] args) {
        SpringApplication.run(MessageServerApplication.class, args);
        log.info("\n message server start");

    }
}