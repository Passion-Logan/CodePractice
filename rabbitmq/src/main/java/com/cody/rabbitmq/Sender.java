package com.cody.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("directExchange", "helloRouting", map);
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

    /**
     * 测试消息回调确认：
     * 第二种情况：消息推送到server，找到交换机了，但是没找到队列
     * 把消息推送到名为‘lonelyDirectExchange’的交换机上（这个交换机是没有任何队列配置的）
     */
    public void TestMessageAck1() {
        String messageData = "message: lonelyDirectExchange test message ";
        Map<String, Object> map = new HashMap<>(1);
        map.put("messageData", messageData);
        rabbitTemplate.convertAndSend("lonelyDirectExchange", "TestDirectRouting", map);
    }

    /**
     * 测试消息回调确认：
     * 第三种情况：消息推送到server，交换机和队列啥都没找到
     * 这种情况其实一看就觉得跟①很像，没错 ，③和①情况回调是一致的
     */

    /**
     * 测试消息回调确认：
     * 第四种情况：消息推送成功
     */
}
