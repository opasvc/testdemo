package com.tencent.user.listener;

import com.tencent.user.config.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RabbitListener(queues = RabbitMqConfig.QUEUE_NAME)
public class RabbitMQListener {

    @RabbitHandler(isDefault = true)
    public void rabbitSer(String msg) {
        log.info("rabbit监听器开始执行: ${}", msg);
    }
}
