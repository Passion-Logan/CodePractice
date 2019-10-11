package com.cody.util;

import java.util.List;

/**
 * 自定义枚举单例对象
 * <p>
 * 代码描述
 * <p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved.
 * <p>
 *
 * @author WQL
 * @since 2019年10月11日 0011 15:07
 */
public enum StringUtil {
    ;

    /**
     * 获取List参数值
     * 
     * @param list
     * @return
     */
    public static String getListString(List<String> list) {
        StringBuilder result = new StringBuilder();
        for (String s : list) {
            result.append(s).append(" ");
        }
        return result.toString();
    }
}
