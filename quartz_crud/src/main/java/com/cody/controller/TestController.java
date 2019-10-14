package com.cody.controller;

import com.cody.entity.model.ScheduleJobModel;
import com.cody.service.ScheduleJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved. <p>
 *
 * @author WQL
 * @since 2019年10月12日 0012 18:19
 */
@RestController
@RequestMapping("api")
public class TestController {

    @Autowired
    private ScheduleJobService service;

    /**
     * 开启
     *
     * @param model
     * @return
     */
    @PostMapping("start")
    public String startSchedule(@RequestBody ScheduleJobModel model) {
        service.startSchedule(model);
        return "ok";
    }

    /**
     * 更新
     *
     * @param model
     * @return
     */
    @PostMapping("update")
    public String scheduleUpdateCorn(@RequestBody ScheduleJobModel model) {
        service.scheduleUpdateCorn(model);
        return "ok";
    }

    /**
     * 暂停
     *
     * @param model
     * @return
     */
    @PostMapping("/pause")
    public String schedulePause(@RequestBody ScheduleJobModel model) {
        service.schedulePause(model);
        return "ok";
    }

    /**
     * 恢复
     *
     * @param model
     * @return
     */
    @PostMapping("/resume")
    public String scheduleResume(@RequestBody ScheduleJobModel model) {
        service.scheduleResume(model);
        return "ok";
    }

    /**
     * 删除一个定时任务
     *
     * @param model
     * @return
     */
    @PostMapping("/delete")
    public String scheduleDelete(@RequestBody ScheduleJobModel model) {
        service.scheduleDelete(model);
        return "ok";
    }

    /**
     * 删除全部定时任务
     *
     * @return
     */
    @PostMapping("deleteAll")
    public String scheduleDeleteAll() {
        service.scheduleDeleteAll();
        return "ok";
    }
}
