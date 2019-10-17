package date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * 应用模块名称<p>
 * Java8新特性时间日期库DateTime<p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved. <p>
 *
 * @author WQL
 * @since 2019年10月17日 0017 17:41
 */
public class localdate {
    /**
     * Java8常用的日期和时间类包含LocalDate、LocalTime、Instant、Duration、Period、LocalDateTime以及ZonedDateTime等。
     * 1、LocalDate：不包含时间的日期，比如2019-10-14。可以用来存储生日，周年纪念日，入职日期等。
     * 2、LocalTime：与LocalDate想对照，它是不包含日期的时间。
     * 3、LocalDateTime：包含了日期及时间，没有偏移信息（时区）。
     * 4、ZonedDateTime：包含时区的完整的日期时间，偏移量是以UTC/格林威治时间为基准的。
     * 5、Instant：时间戳，与System.currentTimeMillis()类似。
     * 6、Duration：表示一个时间段。
     * 7、Period：用来表示以年月日来衡量一个时间段。
     */

    public static void main(String[] args) {
        // 只获取日期
        LocalDate today = LocalDate.now();
        System.out.println(today);

        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();

        System.out.printf("Year : %d Month : %d day : %d \t %n", year, month, day);

        // 月份中的第几天
        int dayOfMonth = today.getDayOfMonth();
        // 一周的第几天
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        // 月份的天数
        int length = today.lengthOfMonth();
        // 是否为闰年
        boolean leapYear = today.isLeapYear();

        // 也可以通过静态方法of()或parse创建任意一个日期
        LocalDate oneDay = LocalDate.of(2019,10,1);
        System.out.println(oneDay);
        // 另外使用before和after可以比较两个日期前后时间。
        boolean notBefore = LocalDate.parse("2019-10-01").isBefore(LocalDate.parse("2019-10-02"));
        boolean isAfter = LocalDate.parse("2019-10-01").isAfter(LocalDate.parse("2019-10-02"));
        // 日期进行前一天后一天或前一个月的加减
        LocalDate tomorrowDay = LocalDate.now().plusDays(1);
        LocalDate nextMonth =  LocalDate.now().plusMonths(1);
        // 获取某一天的开始时间和当天所在月的第一天
        LocalDate.now().atStartOfDay();
        LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
    }
}
