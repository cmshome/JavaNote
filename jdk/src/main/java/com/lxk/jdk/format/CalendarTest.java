package com.lxk.jdk.format;

import org.junit.Test;
import org.w3c.dom.ranges.RangeException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 测试 Calendar
 *
 * @author LiXuekai on 2019/7/18
 */
public class CalendarTest {
    /**
     * 能处理的最早时间，早于这个时间的数据将不再创建索引
     */
    private int startDate;

    @Test
    public void test() {
        // 获取当前时间之后一小时的时间和最早时间
        Calendar per = getNowDate();
        Calendar last = (Calendar) per.clone();

        System.out.println("加1小时");
        outTime(last.getTime());
        last.add(Calendar.HOUR_OF_DAY, 1);
        outTime(last.getTime());
        System.out.println("----------");

        System.out.println("加  HOUR  1小时");
        int range;
        range = Calendar.HOUR;
        setZero(last);
        outTime(last.getTime());
        last.add(range, 1);
        outTime(last.getTime());
        System.out.println("----------");

        System.out.println("加 DATE 一天");
        last.add(Calendar.DATE, 1);
        outTime(last.getTime());
        System.out.println("-----");

        System.out.println("加 MONTH 一月");
        last.add(Calendar.MONTH, 1);
        outTime(last.getTime());
        System.out.println("-----");

        System.out.println("加 YEAR 一月");
        last.add(Calendar.YEAR, 1);
        outTime(last.getTime());
        System.out.println("-----");

        setZero(per);
        while (last.after(per)) {
            int end = (int) (last.getTime().getTime() / 1000);
            last.add(range, -1);
            int start = (int) (last.getTime().getTime() / 1000);
            try {
            } catch (RangeException e) {
            }
        }
        startDate = (int) (per.getTime().getTime() / 1000);
    }

    private void setZero(Calendar c) {
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
    }

    private Calendar getNowDate() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c;
    }

    private static void outTime(Date date) {
        String formatString = "_yyyy-MM-dd_HH";
        SimpleDateFormat sdf = new SimpleDateFormat(formatString);
        String dateString = sdf.format(date);
        //_2019-07-18_15
        System.out.println(dateString);
    }
}
