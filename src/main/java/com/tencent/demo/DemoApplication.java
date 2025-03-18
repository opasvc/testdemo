package com.tencent.demo;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class DemoApplication {

    public static void main(String[] args) {
        setProxy();
        SpringApplication.run(DemoApplication.class, args);
        System.out.println("OK!!!");
    }

    //设置代理
    private static void setProxy() {
        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "23345");
    }

}
