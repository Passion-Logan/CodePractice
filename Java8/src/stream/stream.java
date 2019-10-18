package stream;
import	java.util.Comparator;

import optional.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static javax.swing.UIManager.put;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved. <p>
 *
 * @author WQL
 * @since 2019年10月18日 0018 15:23
 */
public class stream {

    public static void main(String[] args) {
        Person person = new Person();
        person.setName("test");
        person.setAge(14);

        List<Person> sons = new ArrayList<Person>(){{
            add(new Person(){{put("test", 14);}});
            add(new Person(){{put("test2", 15);}});
        }};

        stream(sons);
    }

    public static void stream(List<Person> list) {
        System.out.println(list.stream().map(Person::getName).collect(Collectors.joining()));
    }
}
