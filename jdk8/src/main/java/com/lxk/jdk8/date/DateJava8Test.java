package com.lxk.jdk8.date;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 测试 Java 1.8 日期 API 的使用
 *
 * @author lxk on 2017/12/26
 */
public class DateJava8Test {

    /**
     * Date 和 LocalDate 互相转换。
     */
    @Test
    public void getYMD() {
        //Date -> LocalDate
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy MM dd HH mm ss");
        System.out.println(simpleDateFormat.format(date));
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        System.out.println(localDate);


        //LocalDate ->Date
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        Date ls = Date.from(zdt.toInstant());
        System.out.println("LocalDate = " + localDate);
        System.out.println("Date = " + simpleDateFormat.format(ls));

        //out format date out
        System.out.println("--------");
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter sf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(localDateTime.format(sf));
    }

    /**
     * 日期格式化测试
     * yyyy：年
     * MM：月
     * dd：日
     * hh：1~12小时制(1-12)
     * HH：24小时制(0-23)
     * mm：分
     * ss：秒
     * S：毫秒
     * E：星期几
     * D：一年中的第几天
     * F：一月中的第几个星期(会把这个月总共过的天数除以7)
     * w：一年中的第几个星期
     * W：一月中的第几星期(会根据实际情况来算)
     * a：上下午标识
     * k：和HH差不多，表示一天24小时制(1-24)。
     * K：和hh差不多，表示一天12小时制(0-11)。
     * z：表示时区
     */
    @Test
    public void dateTimeFormatterTest() {
        /*
         * 解析日期
         */
        String dateStr = "2016年10月25日";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        System.out.println(date);

        /*
         * 日期转换为字符串
         */
        LocalDateTime now = LocalDateTime.now();
        System.out.println("未格式化时：" + now);
        //HH 和 hh 的差别：前者是24小时制，后者是12小时制。
        String formatString = "yyyy年MM月dd日 HH:mm:ss:SSS" +
                " 上下午标志 a" +
                " E" +
                " 一年中的第D天" +
                " 一月中的第F个星期" +
                " 一年中的第w个星期" +
                " 一月中的第W个星期";
        DateTimeFormatter format = DateTimeFormatter.ofPattern(formatString);
        System.out.println(now.format(format));
        format = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        System.out.println(now.format(format));
        format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm a");
        System.out.println(now.format(format));
    }

    @Test
    public void easyTest() {
        LocalDateTime localDateTime = LocalDateTime.now();
        //把当前时间修改为11月，其他部分不变。
        System.out.println(localDateTime.withMonth(11));
        //最后都变成秒啦，所以，纳秒部分归零与否，已经不影响啦。
        long second = localDateTime.withHour(0).withMinute(0).withSecond(0).atZone(ZoneId.systemDefault()).toEpochSecond();
        System.out.println(second);
        second = localDateTime.withHour(0).withMinute(0).withSecond(0).withNano(0).atZone(ZoneId.systemDefault()).toEpochSecond();
        System.out.println(second);
        //ZoneOffset继承ZoneId，调用的都是一个底层方法。
        // 打印结果：Asia/Shanghai
        System.out.println(ZoneOffset.systemDefault());
        System.out.println(ZoneId.systemDefault());
    }

}
