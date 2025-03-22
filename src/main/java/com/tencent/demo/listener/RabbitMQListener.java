package com.tencent.demo.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = "queue-hello")
public class RabbitMQListener {
    @RabbitHandler(isDefault = true)
    public void process(String message) {
        System.out.println("\n\n"+message);
    }
}
