package com.cody.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

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
    private AmqpTemplate rabbitTemplate;

    /**
     * 生产者，使用AmqpTemplate接口操作
     * AmqpTemplate接口定义了一套针对AMQP协议的基础操作
     */
    public void send() {
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }
}
