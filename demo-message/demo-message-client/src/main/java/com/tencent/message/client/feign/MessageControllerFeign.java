package com.tencent.message.client.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@FeignClient(name = "msg-server", path = "/message",url = "http://10.147.20.132:18080/msg-server")
@FeignClient(name = "msg-server", path = "/message")
public interface MessageControllerFeign {

    @GetMapping("/test")
    public String testSendMessage();
}
