package com.tencent.user.controller;

import com.tencent.user.config.RabbitMqConfig;
import com.alibaba.fastjson2.JSON;
import com.tencent.user.entity.Student;
import jakarta.annotation.Resource;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return student.toString();
    }
}
