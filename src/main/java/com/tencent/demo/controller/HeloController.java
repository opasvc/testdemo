package com.tencent.demo.controller;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tencent.demo.entity.Student;
import com.tencent.demo.service.StudentService;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping()
public class HeloController {


    @Autowired
    private StudentService studentService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/hello")
    public Object hello() throws IOException {

        rabbitTemplate.send("queue-hello", new Message("hello".getBytes()));

        return "execute.body().bytes()";
    }

    @GetMapping("/find")
    public List<Student> findStudent() {

        List<Student> list = studentService.list();
        list.forEach(System.out::println);
        return list;

    }

}
