package stream;
import	java.util.Comparator;

import optional.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.reducing;
import static javax.swing.UIManager.put;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved. <p>
 *
 * @author WQL
 * @since 2019年10月18日 0018 15:23
 */
public class Stream {

    public static void main(String[] args) {
        Person person = new Person();
        person.setName("test");
        person.setAge(14);

        List<Person> sons = new ArrayList<Person>(){{
            add(new Person(){{setAge(14);setName("test");}});
            add(new Person(){{setAge(15);setName("test2");}});
        }};

        stream(sons);

        Stream s = new Stream();
        s.partition();
    }

    public static void stream(List<Person> list) {
        System.out.println(list.stream().collect(Collectors.summarizingInt(Person::getAge)).getMax());
        System.out.println(list.stream().map(Person::getName).collect(Collectors.joining()));
        System.out.println(list.stream().map(Person::getName).collect(Collectors.toList()));
        // 自定义归约reducing，举例：求和
        System.out.println(list.stream().collect(reducing(0, Person::getAge, (i, j) -> i + j)));
        // 写法二 使用内置函数代替箭头函数
        System.out.println(list.stream().collect(reducing(0, Person::getAge, Integer::sum)));
        // 写法三 直接使用reduce 返回一个optional对象
        System.out.println(list.stream().map(Person::getAge).reduce(Integer::sum));
        // 效率最高的写法
        System.out.println(list.stream().mapToInt(Person::getAge).sum());
    }

    /**
     * 定义一个 质数区分函数
     * @param candidate
     * @return
     */
    private boolean isPrime(int candidate) {
        int candidateRoot =(int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }

    /**
     * 对100以内的数字分组 分为质数和非质数
     */
    public void partition() {
        Map<Boolean, List<Integer>> partitionPrimes = IntStream.rangeClosed(2, 100).boxed()
                .collect(partitioningBy(this::isPrime));

        System.out.println(partitionPrimes.toString());
    }
}
