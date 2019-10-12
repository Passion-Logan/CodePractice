package com.cody.service;

import com.cody.dao.ScheduleJobDaoRepository;
import com.cody.entity.ScheduleJobPo;
import com.cody.entity.model.ScheduleJobModel;
import com.cody.job.ScheduleQuartzJob;
import com.cody.util.DateUtil;
import javafx.geometry.Pos;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

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
     *
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
     *
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

    /**
     * 开启定时任务
     *
     * @param model
     */
    public void startSchedule(ScheduleJobModel model) {
        if (StringUtils.isEmpty(model.getGroupName()) || StringUtils.isEmpty(model.getJobName()) || StringUtils.isEmpty(model.getCron())) {
            throw new RuntimeException("参数不能为空");
        }
        List<ScheduleJobPo> poList = repository.findByGroupNameAndJobNameAndStatus(model.getGroupName(), model.getJobName(), 0);
        if (!ObjectUtils.isEmpty(poList)) {
            throw new RuntimeException("group和job名称已存在");
        }
        try {
            startJob(scheduler, model.getGroupName(), model.getJobName(), model.getCron());
            scheduler.start();
            ScheduleJobPo scheduleJobPo = new ScheduleJobPo();
            scheduleJobPo.setGroupName(model.getGroupName());
            scheduleJobPo.setJobName(model.getJobName());
            scheduleJobPo.setCron(model.getCron());
            scheduleJobPo.setStatus(0);
            scheduleJobPo.setCreateTime(DateUtil.getCurrentTimeStamp());
            scheduleJobPo.setUpdateTime(DateUtil.getCurrentTimeStamp());
            repository.save(scheduleJobPo);
        } catch (Exception e) {
            log.error("exception:{}", e);
        }
    }

    /**
     * 更新定时任务
     *
     * @param model
     */
    public void scheduleUpdateCorn(ScheduleJobModel model) {
        if (ObjectUtils.isEmpty(model.getId()) || ObjectUtils.isEmpty(model.getCron())) {
            throw new RuntimeException("定时任务不存在");
        }
        try {
            ScheduleJobPo po = repository.findByIdAndStatus(model.getId(), 0);
            // 获取触发器
            TriggerKey triggerKey = new TriggerKey(po.getJobName(), po.getGroupName());
            CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            String oldTime = cronTrigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(model.getCron())) {
                CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(model.getCron());
                CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(po.getJobName(), po.getGroupName())
                        .withSchedule(cronScheduleBuilder).build();
                // 更新定时任务
                scheduler.rescheduleJob(triggerKey, trigger);
                po.setCron(model.getCron());
                // 更新数据库
                repository.save(po);
            }
        } catch (Exception e) {
            log.info("exception:{}", e);
        }
    }

    /**
     * 暂停定时任务
     *
     * @param model
     */
    public void schedulePause(ScheduleJobModel model) {
        if (ObjectUtils.isEmpty(model.getId())) {
            throw new RuntimeException("定时任务不存在");
        }
        ScheduleJobPo po = repository.findByIdAndStatus(model.getId(), 0);
        if (ObjectUtils.isEmpty(po)) {
            throw new RuntimeException("定时任务不存在");
        }
        try {
            JobKey jobKey = new JobKey(po.getJobName(), po.getGroupName());
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if (jobDetail == null) {
                return;
            }
            scheduler.pauseJob(jobKey);
            po.setStatus(2);
            repository.save(po);
        } catch (Exception e) {
            log.error("exception:{}", e);
        }
    }

    
}
