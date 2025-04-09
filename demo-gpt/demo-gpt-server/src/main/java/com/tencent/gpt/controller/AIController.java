package com.tencent.gpt.controller;

import com.tencent.gpt.service.DeepSeekService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@Slf4j
@RestController
@RequestMapping
public class AIController {

    @Resource
    private DeepSeekService deepSeekService;

    @GetMapping("/call")
    public String call(@RequestParam String msg) {
        log.info("用户问题: {}", msg);
        return deepSeekService.deepSeek(msg);
//        return "hello ai";
    }

    @GetMapping(value = "/chat", produces = "application/json;charset=UTF-8")
    public Flux<String> chat(@RequestParam String msg) {
        log.info("用户发言: {}", msg);
        return deepSeekService.deepSeekChat(msg);
    }
//    {
//        log.info("用户问题: {}", msg);
//        SseEmitter sseEmitter = new SseEmitter(30L);// 0L 表示不设置超时时间
////        开启一个新线程流式相应给调用者
//        Executors.newSingleThreadExecutor().execute(() -> {
//            try {
//                sseEmitter.send(deepSeekService.deepSeek(msg));
//
//                sseEmitter.complete();
//            } catch (Exception e) {
//                sseEmitter.completeWithError(e);
//            }
//        });
//        return sseEmitter;
////        return "hello ai";
//    }
}
