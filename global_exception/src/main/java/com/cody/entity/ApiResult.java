package com.cody.entity;

import org.springframework.util.ObjectUtils;

import java.io.Serializable;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved. <p>
 *
 * @author WQL
 * @since 2019年10月21日 0021 15:00
 */
public class ApiResult implements Serializable {

    /**
     * 禁止new创建对象
     */
    private ApiResult() {}

    /**
     * 统一创建ApiResultBean对象
     * 方便后期扩展
     * @return
     */
    public static ApiResult newInstance() {
        return new ApiResult();
    }

    /**
     * 消息提示
     */
    private String msg;
    /**
     * 状态信息
     */
    private boolean flag = true;
    /**
     * 返回结构
     */
    private Object result;
    /**
     * 查询出的结构总数
     */
    private int rows;
    /**
     * 需要跳转的路径
     */
    private String jumpUrl;
    /**
     * 接口响应时间单位毫秒
     */
    private long time;


}