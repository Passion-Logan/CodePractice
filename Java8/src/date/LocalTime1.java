package date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved. <p>
 *
 * @author WQL
 * @since 2019年10月17日 0017 17:59
 */
public class LocalTime1 {

    public static void main(String[] args) {
        java.time.LocalTime localTime = java.time.LocalTime.now();

        java.time.LocalTime oneTime = java.time.LocalTime.of(10,10,10);

        // LocalDateTime与LocalDate和LocalTime之间可以相互转化
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime oneTime2 = LocalDateTime.of(2019,10,14,10,12,12);

        // 拼接日期
        java.time.LocalTime.now().atDate(LocalDate.now());

        // 时间日期格式化
        LocalDateTime dateTime = LocalDateTime.now();
        String str = dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        System.out.println(str);
        str = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(str);
    }
}
