package lambda;
import java.util.Arrays;
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
        List<String> list = Arrays.asList("你好", "世界", "hello", "world");
        filter(list, (str) -> ("hello".equals(str)));
        filter(list, (str) -> (((String) str).length() == 2));

        // 如果需要同时满足两个条件或满足其中一个即可，Predicate可以将这样的多个条件合并成一个。
        Predicate start = (str) -> (((String) str).startsWith("0"));
        Predicate len = (str) -> (((String) str).length() == 5);

        // 满足一个条件
        list.stream().filter(start).forEach(System.out::println);
        // 满足两个条件中的一个
        list.stream().filter(start.or(len)).forEach(System.out::println);
    }

    public static void filter(List<String> list, Predicate condition) {
        for (String content : list) {
            if (condition.test(content)) {
                System.out.println("符合条件的内容：" + content);
            }
        }
    }
}
