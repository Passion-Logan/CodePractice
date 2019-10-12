package com.cody.service;

import com.cody.dao.ScheduleJobDaoRepository;
import com.cody.entity.ScheduleJobPo;
import com.cody.job.ScheduleQuartzJob;
import javafx.geometry.Pos;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved. <p>
 *
 * @author WQL
 * @since 2019年10月12日 0012 16:13
 */
@Service
@Slf4j
public class ScheduleJobService {

    @Autowired
    private ScheduleJobDaoRepository repository;

    /**
     * Quartz定时任务核心的功能实现类
     */
    private Scheduler scheduler;

    public ScheduleJobService(@Autowired SchedulerFactoryBean schedulerFactoryBean) {
        scheduler = schedulerFactoryBean.getScheduler();
    }

    /**
     * 项目重启后，初始化原本已经运行的定时任务
     */
    @PostConstruct
    public void init() {
        List<ScheduleJobPo> poList = repository.findAllByStatus(0);
        poList.forEach(po -> {
            startScheduleByInit(po);
        });
    }

    /**
     * 初始化时开启定时任务
     * @param po
     */
    private void startScheduleByInit(ScheduleJobPo po) {
        try {
            startJob(scheduler, po.getGroupName(), po.getJobName(), po.getCron());
            scheduler.start();
        } catch (Exception e) {
            log.error("exception:{}", e);
        }
    }

    /**
     * 开启任务
     * @param scheduler
     * @param group
     * @param name
     * @param cron
     * @throws SchedulerException
     */
    private void startJob(Scheduler scheduler, String group, String name, String cron) throws SchedulerException {
        JobDataMap map = new JobDataMap();
        map.put("group", group);
        map.put("name", name);
        JobDetail jobDetail = JobBuilder.newJob(ScheduleQuartzJob.class).withIdentity(name, group)
                .usingJobData(map)
                .build();
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(name, group)
                .withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }


}
