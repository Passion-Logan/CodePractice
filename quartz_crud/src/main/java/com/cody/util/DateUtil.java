package com.cody.util;

import java.util.Date;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved. <p>
 *
 * @author WQL
 * @since 2019年10月12日 0012 17:07
 */
public class DateUtil {

    /**
     * 得到当前时间戳
     *
     * @return
     */
    public static Long getCurrentTimeStamp() {
        long timeMillis = System.currentTimeMillis();
        return timeMillis;
    }
}
