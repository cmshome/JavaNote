package com.lxk.jdk8.date;

import com.lxk.tool.TimeUtils;
import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * LocalDateTime test
 * 在新的Java 8中，日期和时间被明确划分为LocalDate和LocalTime，
 * LocalDate无法包含时间；
 * LocalTime无法包含日期；
 * LocalDateTime才能同时包含日期和时间。
 *
 * @author LiXuekai on 2019/9/10
 */
public class LocalDateTimeTest {

    @Test
    public void format() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String format = TimeUtils.format(localDateTime);
        System.out.println(format);
    }

    @Test
    public void toEpochDay() {
        LocalTime localTime = LocalDateTime.now().toLocalTime();
        long l = LocalDateTime.now().toLocalDate().toEpochDay();
        System.out.println(l);
        DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate parse = LocalDate.parse("20191031", yyyyMMdd);
        System.out.println(parse.toEpochDay());
    }

    /**
     * 将秒数格式化成日期字符串输出
     */
    @Test
    public void turnSecondsToData() {
        LocalDateTime localDateTime = TimeUtils.castSecondsToLocalDateTime(TimeUtils.nowS());
        System.out.println(localDateTime.getDayOfMonth());
        LocalDateTime localDateTime1 = localDateTime.withDayOfMonth(11);

        System.out.println(TimeUtils.format(localDateTime1));
        System.out.println(TimeUtils.castLocalDateTimeToSeconds(localDateTime1));
    }

    /**
     * 由秒数转成Java8时间类操作对象
     */
    @Test
    public void secondToJava8Date() {
        long second = TimeUtils.nowS();
        System.out.println(second);

        LocalDateTime localDateTime = TimeUtils.castSecondsToLocalDateTime(second);
        LocalDateTime dateTime = localDateTime.plusDays(-1);

        long second1 = TimeUtils.castLocalDateTimeToSeconds(dateTime);
        System.out.println(second1);

        System.out.println(TimeUtils.format(localDateTime));
        System.out.println(TimeUtils.format(dateTime));

    }

    /**
     * jdk 1.8 中的 LocalDateTime 的使用
     */
    @Test
    public void localDateTimeTest() {
        System.out.println("-----------test java 8 LocalDateTime-----------");
        //当前时间，以秒为单位。
        long epochSecond = TimeUtils.nowS();
        //默认使用系统时区
        ZoneId zoneId = ZoneOffset.systemDefault();
        //之所以这么初始化，是因为根据传入的时间进行操作
        LocalDateTime localDateTime = TimeUtils.castSecondsToLocalDateTime(epochSecond);
        //LocalDateTime.now();//也可以这么获得当前时间
        System.out.println("localDateTime 初始化值：" + localDateTime);
        System.out.println("getYear：" + localDateTime.getYear());
        //方法返回值类型特殊，是枚举类型：Month类型
        System.out.println("getMonth：" + localDateTime.getMonth());
        System.out.println("getDayOfMonth：" + localDateTime.getDayOfMonth());
        System.out.println("getHour：" + localDateTime.getHour());
        System.out.println("getMinute：" + localDateTime.getMinute());
        System.out.println("getSecond：" + localDateTime.getSecond());
        System.out.println("getNano：" + localDateTime.getNano());
        System.out.println("getDayOfWeek：" + localDateTime.getDayOfWeek());

        /*
         * 获得传入时间的某一天的凌晨零分零秒的秒数
         */
        long dayStart = TimeUtils.dayStartSecond(localDateTime);
        System.out.println("dayStart 时间戳，秒数：" + dayStart);
        /*
         * 获得传入时间的周一的凌晨零分零秒的秒数
         */
        localDateTime = LocalDateTime.of(2017, 12, 2, 0, 0, 0);
        System.out.println("localDateTime 设置当前值：" + localDateTime);
        System.out.println("getDayOfWeek：" + localDateTime.getDayOfWeek());
        System.out.println("getDayOfWeek 的 ordinal 值：" + localDateTime.getDayOfWeek().ordinal());
        System.out.println("getDayOfWeek 的 value 就是周几的值：" + localDateTime.getDayOfWeek().getValue());
        LocalDateTime weekStart = localDateTime.minusDays(localDateTime.getDayOfWeek().ordinal()).withHour(0).withMinute(0).withSecond(0);
        System.out.println("weekStart：" + weekStart);
        /*
         * 获得传入时间的月份的第一天的凌晨零分零秒的秒数
         */
        long monthStart = localDateTime.with(TemporalAdjusters.firstDayOfMonth()).withHour(0).withMinute(0).withSecond(0).atZone(zoneId).toEpochSecond();
        System.out.println("monthStart 时间戳，秒数：" + monthStart);

        /*
         * 传入时间的年的第一天
         */
        LocalDateTime firstDayOfYear = localDateTime.with(TemporalAdjusters.firstDayOfYear());
        System.out.println("firstDayOfYear：" + firstDayOfYear);

        /*
         * 当前时间，往后推一周的时间。plus   加
         */
        LocalDateTime plusWeeks = localDateTime.plusWeeks(1);
        System.out.println("plus one week：" + plusWeeks);
        /*
         * 当前时间，向前推一周的时间。minus  减
         */
        LocalDateTime minusWeeks = localDateTime.minusWeeks(1);
        System.out.println("minus one week：" + minusWeeks);

        DateTimeFormatter sf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String startTime = "2016-11-13 23:59";
        localDateTime = LocalDateTime.parse(startTime, sf);
        System.out.println(localDateTime);
        //格式化一下
        System.out.println(localDateTime.format(sf));
    }


    /**
     * 对时间进行  加减操作。
     */
    @Test
    public void mathTest() {
        DateTimeFormatter sf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.format(sf));

        LocalDateTime localDateTime1 = localDateTime.minusYears(1);
        System.out.println(localDateTime1.format(sf));
    }

}
