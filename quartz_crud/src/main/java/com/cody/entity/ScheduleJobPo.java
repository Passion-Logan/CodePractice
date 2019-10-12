package com.cody.entity;
import lombok.Data;

import javax.persistence.*;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved. <p>
 *
 * @author WQL
 * @since 2019年10月12日 0012 15:40
 */
@Data
@Table(name = "tbl_schedule_job")
@Entity
public class ScheduleJobPo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 任务group名称
     */
    @Column(name = "group_name")
    private String groupName;

    /**
     * 任务job名称
     */
    @Column(name = "job_name")
    private String jobName;

    /**
     * cron表达式
     */
    @Column(name = "cron")
    private String cron;

    /**
     * 0 - 代表正在执行  1 - 已删除  2 - 暂停
     */
    @Column(name = "status")
    private Integer status;

    @Column(name = "create_time")
    private Long createTime;

    @Column(name = "update_time")
    private Long updateTime;
}
