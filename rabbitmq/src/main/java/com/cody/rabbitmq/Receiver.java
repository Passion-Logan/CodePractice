package com.cody.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved. <p>
 *
 * @author WQL
 * @since 2019年10月22日 0022 16:32
 */
@Component
@RabbitListener(queues = "hello")
public class Receiver {

    /**
     * 该消费者者实现了对hello队列的消费
     * 消费操作为输出消息的字符内容
     *
     * @param hello
     */
    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver : " + hello);
    }
}
