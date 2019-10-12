package com.cody.entity.model;

import lombok.Data;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved. <p>
 *
 * @author WQL
 * @since 2019年10月12日 0012 16:59
 */
@Data
public class ScheduleJobModel {

    private Integer id;

    private String groupName;

    private String jobName;

    private String cron;
}
