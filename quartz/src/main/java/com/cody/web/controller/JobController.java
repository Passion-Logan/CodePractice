package com.cody.web.controller;

import com.cody.dao.JobEntityRepository;
import com.cody.entity.JobEntity;
import com.cody.service.DynamicJobService;
import com.cody.web.dto.ModifyCronDTO;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

/**
 * 应用模块名称
 * <p>
 * 代码描述
 * <p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved.
 * <p>
 *
 * @author WQL
 * @since 2019年10月11日 0011 15:55
 */
@RestController
@Slf4j
public class JobController {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    private DynamicJobService jobService;

    @Autowired
    private JobEntityRepository repository;

    public void initialize() {

    }

    /**
     * 根据ID重启某个Job
     * 
     * @param id
     * @return
     * @throws SchedulerException
     */
    @RequestMapping("/refresh/{id}")
    public String refresh(@PathVariable @NotNull Integer id) throws SchedulerException {
        String result;
        JobEntity entity = jobService.getJobEntityById(id);
        if (Objects.isNull(entity)) {
            return "error: id is not exist ";
        }
        synchronized (log) {
            JobKey jobKey = jobService.getJobKey(entity);
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            scheduler.pauseJob(jobKey);
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobKey.getName(), jobKey.getGroup()));
            scheduler.deleteJob(jobKey);
            JobDataMap map = jobService.getJobDataMap(entity);
            JobDetail jobDetail = jobService.getJobDetail(jobKey, entity.getDescription(), map);
            if ("OPEN".equals(entity.getStatus())) {
                scheduler.scheduleJob(jobDetail, jobService.getTrigger(entity));
                result = "Refresh Job : " + entity.getName() + "\t jarPath: " + entity.getJarPath() + " success !";
            } else {
                result = "Refresh Job : " + entity.getName() + "\t jarPath: " + entity.getJarPath() + " failed ! , "
                    + "Because the Job status is " + entity.getStatus();
            }
        }

        return result;
    }

    /**
     * 重启数据库中所有的Job
     * 
     * @return
     */
    public String refreshAll() {
        String result;
        try {
            reStartAllJobs();
            result = "success";
        } catch (SchedulerException e) {
            result = "exception : " + e.getMessage();
        }

        return "refresh all jobs : " + result;
    }

    /**
     * 重新启动所有的job
     * 
     * @throws SchedulerException
     */
    private void reStartAllJobs() throws SchedulerException {
        // 只允许一个线程进入操作
        synchronized (log) {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            Set<JobKey> set = scheduler.getJobKeys(GroupMatcher.anyGroup());
            // 暂停所有JOB
            scheduler.pauseJobs(GroupMatcher.anyGroup());
            // 删除从数据库中注册的所有JOB
            for (JobKey jobKey : set) {
                scheduler.unscheduleJob(TriggerKey.triggerKey(jobKey.getName(), jobKey.getGroup()));
                scheduler.deleteJob(jobKey);
            }
            // 从数据库中注册的所有JOB
            for (JobEntity job : jobService.loadJobs()) {
                log.info("Job register name : {} , group : {} , cron : {}", job.getName(), job.getJobGroup(),
                    job.getCron());
                JobDataMap map = jobService.getJobDataMap(job);
                JobKey jobKey = jobService.getJobKey(job);
                JobDetail jobDetail = jobService.getJobDetail(jobKey, job.getDescription(), map);
                if ("OPEN".equals(job.getStatus())) {
                    scheduler.scheduleJob(jobDetail, jobService.getTrigger(job));
                } else {
                    log.info("Job jump name : {} , Because {} status is {}", job.getName(), job.getName(),
                        job.getStatus());
                }
            }
        }
    }

    /**
     * 修改某个Job执行的Cron
     * 
     * @param dto
     * @return
     * @throws SchedulerException
     */
    @PostMapping("/modifyJob")
    public String modifyJob(@RequestBody @Validated ModifyCronDTO dto) throws SchedulerException {
        if (!CronExpression.isValidExpression(dto.getCron())) {
            return "cron is invalid !";
        }
        synchronized (log) {
            JobEntity job = jobService.getJobEntityById(dto.getId());
            if ("OPEN".equals(job.getStatus())) {
                try {
                    JobKey jobKey = jobService.getJobKey(job);
                    TriggerKey triggerKey = new TriggerKey(jobKey.getName(), jobKey.getGroup());
                    Scheduler scheduler = schedulerFactoryBean.getScheduler();
                    CronTrigger cronTrigger = (CronTrigger)scheduler.getTrigger(triggerKey);
                    String oldCron = cronTrigger.getCronExpression();
                    if (!oldCron.equalsIgnoreCase(dto.getCron())) {
                        job.setCron(dto.getCron());
                        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(dto.getCron());
                        CronTrigger trigger =
                            TriggerBuilder.newTrigger().withIdentity(jobKey.getName(), jobKey.getGroup())
                                .withSchedule(cronScheduleBuilder).usingJobData(jobService.getJobDataMap(job)).build();
                        scheduler.rescheduleJob(triggerKey, trigger);
                        repository.save(job);
                    }
                } catch (Exception e) {
                    log.error("printStackTrace", e);
                }
            } else {
                log.info("Job jump name : {} , Because {} status is {}", job.getName(), job.getName(), job.getStatus());
                return "modify failure , because the job is closed";
            }
        }
        return "modify success";
    }
}
