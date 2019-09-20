package com.lxk.jdk8.date;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * LocalDate Test
 * 在新的Java 8中，日期和时间被明确划分为LocalDate和LocalTime，
 * LocalDate无法包含时间；
 * LocalTime无法包含日期；
 * LocalDateTime才能同时包含日期和时间。
 *
 * @author LiXuekai on 2019/9/12
 */
public class LocalDateTest {

    @Test
    public void parseTest(){
        LocalDate endOfDec = LocalDate.parse("2017-12-28");
        //xxxx不能这么做
        DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate parse = LocalDate.parse("20181011", yyyyMMdd);
        System.out.println(parse);
        String format = yyyyMMdd.format(parse);
        System.out.println(format);

    }

    /**
     * jdk 1.8 中的 localDate 的使用
     */
    @Test
    public void localDateTest() {
        System.out.println("-----------test java 8 LocalDate-----------");
        LocalDate today = LocalDate.now();
        System.out.println("当前日期：" + today);
        System.out.println("当前日期的年：" + today.getYear());
        //返回的是枚举类型：Month，Java 8  新增的枚举类型
        System.out.println("当前日期的月--枚举类型：" + today.getMonth());
        //这个返回int才是我们常用的月的数字形式。
        //记得以前你学Java的时候，一月的数字是0吗？这不用你自己手动+1啦，自动就是月份对应的数字，1-12.
        System.out.println("当前日期的月--数字类型：" + today.getMonthValue());
        System.out.println("当前日期的日：" + today.getDayOfMonth());
        //返回的是枚举类型：DayOfWeek，Java 8 新增的枚举类型
        System.out.println("当前日期是周几：" + today.getDayOfWeek());
        System.out.println("当前日期是一年之中的第几天：" + today.getDayOfYear());
        //Chronology：翻译为年代学;年表。此处的返回值是 ISO
        System.out.println("年表：" + today.getChronology());

        LocalDate christmas = LocalDate.of(2017, 12, 25);
        System.out.println("christmas：" + christmas);
        //严格按照ISO yyyy-MM-dd验证，02写成2都不行，当然也有一个重载方法允许自己定义格式
        LocalDate endOfDec = LocalDate.parse("2017-12-28");
        System.out.println("endOfDec：" + endOfDec);
        /*
         * 无效日期无法通过：DateTimeParseException: Invalid date
         */
        //LocalDate.parse("2017-02-29");

        System.out.println("当前日期：" + today);
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("当前日期所在的月的第一天：" + firstDayOfMonth);
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("当前日期所在的月的最后一天：" + lastDayOfThisMonth);
        LocalDate secondDayOfThisMonth = today.withDayOfMonth(2);
        System.out.println("当前日期所在的月的第二天：" + secondDayOfThisMonth);

        //对日期进行加减 plus minus
        System.out.println("当前日期plus一天：" + today.plusDays(1));
        System.out.println("当前日期minus一天：" + today.minusDays(1));
    }
}
