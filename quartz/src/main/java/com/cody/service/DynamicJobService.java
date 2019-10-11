package com.cody.service;

import com.cody.dao.JobEntityRepository;
import com.cody.entity.JobEntity;
import com.cody.job.DynamicJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 应用模块名称
 * <p>
 * 代码描述
 * <p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved.
 * <p>
 *
 * @author WQL
 * @since 2019年10月11日 0011 15:44
 */
@Service
public class DynamicJobService {
    @Autowired
    private JobEntityRepository repository;

    /**
     * 通过Id获取Job
     * 
     * @param id
     * @return
     */
    public JobEntity getJobEntityById(Integer id) {
        return repository.getById(id);
    }

    /**
     * 从数据库中加载获取到所有Job
     * 
     * @return
     */
    public List<JobEntity> loadJobs() {
        return repository.findAll();
    }

    /**
     * 获取JobDataMap.(Job参数对象)
     * 
     * @param job
     * @return
     */
    public JobDataMap getJobDataMap(JobEntity job) {
        JobDataMap map = new JobDataMap();
        map.put("name", job.getName());
        map.put("jobGroup", job.getJobGroup());
        map.put("cronExpression", job.getCron());
        map.put("parameter", job.getParameter());
        map.put("jobDescription", job.getDescription());
        map.put("vmParam", job.getVmParam());
        map.put("jarPath", job.getJarPath());
        map.put("status", job.getStatus());
        return map;
    }

    /**
     * 获取JobDetail,JobDetail是任务的定义,而Job是任务的执行逻辑,JobDetail里会引用一个Job Class来定义
     * 
     * @param jobKey
     * @param description
     * @param map
     * @return
     */
    public JobDetail getJobDetail(JobKey jobKey, String description, JobDataMap map) {
        return JobBuilder.newJob(DynamicJob.class).withIdentity(jobKey).withDescription(description).setJobData(map)
            .storeDurably().build();
    }

    /**
     * 获取Trigger (Job的触发器,执行规则)
     * 
     * @param job
     * @return
     */
    public Trigger getTrigger(JobEntity job) {
        return TriggerBuilder.newTrigger().withIdentity(job.getName(), job.getJobGroup())
            .withSchedule(CronScheduleBuilder.cronSchedule(job.getCron())).build();
    }

    /**
     * 获取JobKey,包含Name和Group
     * 
     * @param job
     * @return
     */
    public JobKey getJobKey(JobEntity job) {
        return JobKey.jobKey(job.getName(), job.getJobGroup());
    }

}
