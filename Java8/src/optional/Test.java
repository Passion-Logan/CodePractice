package optional;

import java.util.Optional;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved. <p>
 *
 * @author WQL
 * @since 2019年10月18日 0018 14:22
 */
public class Test {

    public static void main(String[] args) {
        Person son = new Person();
        son.setName("test");

        System.out.println(getPersonName(son));
        System.out.println(getNameWithOptional(son));
    }

    /**
     * 老式判断非空写法
     * @return
     */
    public static String getPersonName(Person person) {
        if (person != null) {
            return person.getName();
        } else {
            return "--";
        }
    }

    /**
     * 使用optional实现判断非空
     * @param person
     * @return
     */
    public static String getNameWithOptional(Person person) {
        // optional可通过isPresent()方法来判断是否有值
        // flatMap方法与map相识，map方法如果有值则返回指定get方法的值，否则返回空的optional, 而flatMap则只能放回Optional类型的
        // orElseThrow方法与get类似，当值 为空则会抛出指定异常
        Optional.empty().orElseThrow(() -> new RuntimeException("运行错误"));
        // ifPresent方法，可对值进行判断然后打印，接收参数为Consumer函数式接口
        Optional.of("你好：世界").ifPresent(System.out::println);
        return Optional.ofNullable(person).map(Person::getName).orElseGet(() -> {
            String a = "hello ";
            String b = "world";
            return a + b;
        });
    }
}
