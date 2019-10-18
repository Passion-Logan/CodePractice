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
        return Optional.ofNullable(person).map(Person::getName).orElse("--");
    }
}
