package com.cody.job;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 简单使用Spring定时任务，注解方式<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved. <p>
 *
 * @author WQL
 * @since 2019年10月14日 0014 16:45
 */
@Component
@Configurable
@EnableScheduling
public class TestTask {
    @Scheduled(cron = "0/1 * * * * ?")
    public void test() {
        System.out.println("Spring定时任务方法");
    }
}
