package com.lxk.jdk8.date;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * DateTimeFormatter test
 *
 * @author LiXuekai on 2019/9/20
 */
public class DateTimeFormatterTest {

    /**
     * 将ms/s数格式化成日期字符串输出
     */
    @Test
    public void format() {
        ZoneId zoneId = ZoneOffset.systemDefault();
        long currentTimeMillis = System.currentTimeMillis();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(currentTimeMillis), zoneId);
        //LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(System.currentTimeMillis() / 1000), zoneId);
        DateTimeFormatter sf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
        System.out.println(sf.format(localDateTime));
        System.out.println(currentTimeMillis);
    }

    @Test
    public void toEpochDay() {
        DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate parse = LocalDate.parse("20191031", yyyyMMdd);
        System.out.println(parse.toEpochDay());
    }
}
