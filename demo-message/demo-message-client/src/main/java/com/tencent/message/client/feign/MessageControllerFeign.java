package com.tencent.message.client.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "message-server", path = "/message")
public interface MessageControllerFeign {

    @RequestMapping("/test")
    public String testSendMessage();
}
