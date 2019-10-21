package com.cody.exception;

import com.cody.entity.ApiResult;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved. <p>
 *
 * @author WQL
 * @since 2019年10月21日 0021 15:17
 */
public final class ApiResultGenerator {
    public static ApiResult result(boolean flag, String msg, Object result, String jumpUrl, int rows, Throwable throwable) {
        // 创建返回对象
        ApiResult apiResult = ApiResult.newInstance();
        apiResult.
    }
}
