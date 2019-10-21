package com.cody.exception;

import com.cody.entity.ApiResult;

import java.util.List;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved. <p>
 *
 * @author WQL
 * @since 2019年10月21日 0021 15:17
 */
public final class ApiResultGenerator {
    /**
     * 创建普通消息方法
     * @param flag
     * @param msg
     * @param result
     * @param jumpUrl
     * @param rows
     * @param throwable
     * @return
     */
    public static ApiResult result(boolean flag, String msg, Object result, String jumpUrl, int rows, Throwable throwable) {
        // 创建返回对象
        ApiResult apiResult = ApiResult.newInstance();
        apiResult.setFlag(true);
        apiResult.setMsg(msg == "" ? "执行成功" : msg);
        apiResult.setResult(result);
        apiResult.setJumpUrl(jumpUrl);
        apiResult.setTime(System.currentTimeMillis());
        apiResult.setRows(rows);
        return apiResult;
    }

    public static ApiResult successResult(Object result) {
        // rows默认为0
        int rows = 0;
        // 如果是集合
        if (result instanceof List) {
            // 获取总数量
            rows = ((List) result).size();
        }
        return result(true,"",result,"",rows,null);
    }
}
