package lambda;
import	java.util.function.Predicate;

import java.util.List;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved. <p>
 *
 * @author WQL
 * @since 2019年10月17日 0017 17:07
 */
public class lambda {

    public static void main(String[] args) {

    }

    public static void filter(List<String> list, Predicate condition) {
        for (String content : list) {
            if (condition.test(content)) {
                System.out.println("符合条件的内容：" + content);
            }
        }
    }
}
