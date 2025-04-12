package com.tencent.message.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/message")
public class SendMessageController {

    @RequestMapping("/test")
    public String testSendMessage() {
        log.info("send message");
        return "send message";
    }
}
