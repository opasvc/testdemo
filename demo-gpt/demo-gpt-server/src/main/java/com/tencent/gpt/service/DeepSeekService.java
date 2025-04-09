package com.tencent.gpt.service;

import jakarta.annotation.Resource;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class DeepSeekService {

    @Resource
    private OllamaChatModel ollamaModel;

    public String deepSeek(String question) {
        return ollamaModel.call(question);
    }
    public Flux<String> deepSeekChat(String question) {
        return ollamaModel.stream(question);
    }

}
