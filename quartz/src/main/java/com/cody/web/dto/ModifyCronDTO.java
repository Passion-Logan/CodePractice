package com.cody.web.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved. <p>
 *
 * @author WQL
 * @since 2019年10月11日 0011 15:54
 */
@Data
public class ModifyCronDTO {
    @NotNull(message = "the job id cannot be null")
    private Integer id;

    @NotEmpty(message = "the cron cannot be empty")
    private String cron;
}
