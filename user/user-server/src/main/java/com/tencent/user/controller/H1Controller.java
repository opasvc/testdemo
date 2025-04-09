package com.tencent.user.controller;

import com.tencent.user.config.RabbitMqConfig;
import com.alibaba.fastjson2.JSON;
import com.tencent.user.domain.entity.Student;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping()
public class H1Controller {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/ping")
    public String ping() {
        Student student = new Student();
        student.setName("小明");
        String jsonString = JSON.toJSONString(student);
        rabbitTemplate.send(RabbitMqConfig.QUEUE_NAME, new Message(jsonString.getBytes()));
        sendMessage();
        return student.toString();
    }

    @Async
    void sendMessage() {
//        通过OKHttp发送消息
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url("http://localhost:18082/message/send")
                .get()
                .build();
        Call call = client.newCall(request);
        Object obj = null;
        try {
            Response execute = call.execute();
            obj = execute.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("发送消息成功: ${}" + obj);
    }
}
