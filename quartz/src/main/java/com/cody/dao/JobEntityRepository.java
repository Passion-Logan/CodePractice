package com.cody.dao;

import com.cody.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 应用模块名称
 * <p>
 * 代码描述
 * <p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved.
 * <p>
 *
 * @author WQL
 * @since 2019年10月11日 0011 15:05
 */
public interface JobEntityRepository extends JpaRepository<JobEntity, Long> {

    /**
     * 根据id查找任务
     * 
     * @param id
     * @return
     */
    JobEntity getById(Integer id);
}
