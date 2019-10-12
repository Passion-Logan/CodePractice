package com.cody.dao;

import com.cody.entity.ScheduleJobPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved. <p>
 *
 * @author WQL
 * @since 2019年10月12日 0012 16:04
 */
@Repository
public interface ScheduleJobDaoRepository extends JpaRepository<ScheduleJobPo, Integer>, JpaSpecificationExecutor<ScheduleJobPo> {

    /**
     * 根据id和状态码查找任务
     * @param id
     * @param status
     * @return
     */
    public ScheduleJobPo findByIdandAndStatus(Integer id, Integer status);

    /**
     * 获取指定状态的所有任务
     * @param status
     * @return
     */
    public List<ScheduleJobPo> findAllByStatus(Integer status);

    /**
     * 根据任务组名称，任务名称，状态码获取任务
     * @param groupName
     * @param jobName
     * @param status
     * @return
     */
    public List<ScheduleJobPo> findByGroupNameAndJobNameAndStatus(String groupName, String jobName, Integer status);

    /**
     * 根据状态码获取任务，根据创建时间降序
     * @param statusList
     * @return
     */
    public List<ScheduleJobPo> findAllByStatusInOrderByCreateTimeDesc(List<Integer> statusList);

}
