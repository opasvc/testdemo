package com.tencent.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class H1Controller {

    @GetMapping("/ping")
    public String ping() {
        return "timeout";
    }
}
