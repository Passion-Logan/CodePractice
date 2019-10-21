package com.cody.exception;

import com.cody.entity.ApiResult;
import org.springframework.web.bind.annotation.*;

/**
 * result统一异常处理<p>
 *     具体Controller不需要进行单独异常处理
 *     由该类统一处理
 * 代码描述<p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved. <p>
 *
 * @author WQL
 * @since 2019年10月21日 0021 14:34
 */
@ControllerAdvice(annotations = RestController.class)
@ResponseBody
public class RestExceptionHandler {
    /**
     * @ExceptionHandler 用来配置需要拦截的异常类型，默认是全局类型
     * @ResponseStatus 用来配置遇到该异常返回数据时的StatusCode的值，默认使用值500
     * @param e
     * @return
     */
    @ExceptionHandler
    @ResponseStatus
    public ApiResult runtimeExceptionHandler(Exception e) {
        return ApiResultGenerator.errorResult(e.getMessage(), e);
    }
}
