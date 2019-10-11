package com.cody.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 应用模块名称
 * <p>
 * 代码描述
 * <p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved.
 * <p>
 *
 * @author WQL
 * @since 2019年10月11日 0011 14:57
 */
@Entity
@Table(name = "job_entity")
@Data
@Accessors(chain = true)
public class JobEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * job名称
     */
    private String name;
    /**
     * job组名
     */
    private String jobGroup;
    /**
     * 执行的cron
     */
    private String cron;
    /**
     * job的参数
     */
    private String parameter;
    /**
     * job描述信息
     */
    private String description;
    /**
     * vm参数
     */
    private String vmParam;
    /**
     * job的jar路径
     */
    private String jarPath;
    /**
     * job的执行状态,这里我设置为OPEN/CLOSE且只有该值为OPEN才会执行该Job
     */
    private String status;

}
