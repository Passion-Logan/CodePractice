package com.cody.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 生产者<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved. <p>
 *
 * @author WQL
 * @since 2019年10月22日 0022 16:36
 */
@Component
public class Sender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 生产者，使用RabbitTemplate接口操作
     * RabbitTemplate接口定义了一套针对AMQP协议的基础操作
     */
    public void send() {
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }

    /**
     * 测试消息回调确认：
     * 第一种情况：消息推送到server，但是在server里找不到交换机
     * 推送到名为‘non-existent-exchange’的交换机上（这个交换机是没有创建没有配置的）
     */
    public void TestMessageAck() {
        String messageData = "message: non-existent-exchange test message ";
        Map<String, Object> map = new HashMap<>(1);
        map.put("messageData", messageData);
        rabbitTemplate.convertAndSend("non-existent-exchange", "TestDirectRouting", map);
    }
}
