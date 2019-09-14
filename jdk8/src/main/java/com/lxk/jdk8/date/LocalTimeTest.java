package com.lxk.jdk8.date;

import org.junit.Test;

import java.time.LocalTime;

/**
 * LocalTime Test
 * 在新的Java 8中，日期和时间被明确划分为LocalDate和LocalTime，
 * LocalDate无法包含时间；
 * LocalTime无法包含日期；
 * LocalDateTime才能同时包含日期和时间。
 *
 * @author LiXuekai on 2019/9/12
 */
public class LocalTimeTest {

    /**
     * jdk 1.8 中的 LocalTime 的使用
     */
    @Test
    public void localTimeTest() {
        System.out.println("-----------test java 8 LocalTime-----------");
        LocalTime now = LocalTime.now();
        System.out.println("当前时间" + now);
        System.out.println("当前时间：小时--" + now.getHour());
        System.out.println("当前时间：分钟--" + now.getMinute());
        System.out.println("当前时间：秒--" + now.getSecond());
        //纳秒，不是毫秒，是十亿分之一秒。
        System.out.println("当前时间：纳秒--" + now.getNano());
        //清除毫秒数，也就是说，你可以肆意设置时间的任意一个位置的值，使用withXXX()就可以啦。
        System.out.println("当前时间：清空纳秒--" + now.withNano(0));
        System.out.println("当前时间：挨个清零--" + now.withHour(0).withMinute(0).withSecond(0).withNano(0));
    }
}
