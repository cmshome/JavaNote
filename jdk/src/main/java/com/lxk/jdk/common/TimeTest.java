package com.lxk.jdk.common;


import com.lxk.bean.model.TimeFormatModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间相关的测试
 * <p>
 *
 * @author lxk on 2017/2/22
 */
public class TimeTest {
    public static void main(String[] args) {
        //Date date = new Date();
        //Date aw = Calendar.getInstance().getTime();//获得时间的另一种方式，测试效果一样
        //testTime(date);
        //long a = System.currentTimeMillis();
        //System.out.println(t.toString());
        //System.out.println("\r<br> 执行耗时 : " + (System.currentTimeMillis() - a) / 1000f + " 秒 ");
        //testCalendar();
        //testStringToDate();
        //turnLongToDate();
        //testGetTime();
        getIntegerMinute();

    }

    /**
     * 对给的秒数取整分
     */
    private static void getIntegerMinute() {
        long now = 1551945564L;
        long yes = now/60 * 60;
        System.out.println(now);
        System.out.println(yes);
        System.out.println(yes + 60);
    }

    /**
     * 编辑器检测代码，使用推荐方式初始化时间
     * 运行结果没啥不一样啊
     */
    private static void testGetTime() {
        // Positive example:
        long a = System.currentTimeMillis();
        // Negative example:
        long b = new Date().getTime();
        System.out.println("Positive example：" + a);
        System.out.println("Negative example：" + b);
    }

    /**
     * 时间戳 -> 格式化的时间字符串
     */
    private static void turnLongToDate() {
        Date ping = new Date(1506743427564L);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(ping));
    }

    /**
     * 字符串 -> 时间
     */
    private static void testStringToDate() {
        String s = "2017-05-25";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(s);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(date);
    }

    /**
     * 查看 Calendar 获得年月日时分秒
     */
    private static void testCalendar() {
        //1490084570

        //1490084570000L
        //1506582060292
        Date ping = new Date(1490084570000L);
        Calendar cal = Calendar.getInstance();
        TimeFormatModel t = new TimeFormatModel();
        t.setDate(cal.getTime());
        System.out.println(t.toString());
        t.setDate(ping);
        System.out.println(t.toString());
        System.out.println(cal.get(Calendar.YEAR));
        System.out.println(cal.get(Calendar.MONTH));
        System.out.println(cal.get(Calendar.DAY_OF_MONTH) + 1);
        System.out.println(cal.get(Calendar.HOUR_OF_DAY));
        System.out.println(cal.get(Calendar.MINUTE));
        System.out.println(cal.get(Calendar.SECOND));

    }

    /**
     * 将Date类型的时间转成 2016-10-25 18:11:55 此格式
     * 时间戳转换为 2016-10-25 18:11:55 此格式
     *
     * @param ss Date类型的时间
     */
    private static void testTime(Date ss) {
        //打印一般的Date类型的时间
        System.out.println(ss);
        //这个是把当前时间转换成秒数，即时间戳。
        System.out.println(ss.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //这个就是把时间戳经过处理得到期望格式的时间
        String time = format.format(ss.getTime());
        System.out.println(time);
    }
}
