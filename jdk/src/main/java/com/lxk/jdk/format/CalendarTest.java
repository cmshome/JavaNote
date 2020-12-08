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
    private SimpleDateFormat sdf;
    private SimpleDateFormat simpleDateFormat;
    private String index;

    @Test
    public void test() {
        this.sdf = new SimpleDateFormat("_yyyy-MM-dd-HH");
        this.simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        index = "detail";
        // 获取当前时间之后一小时的时间和最早时间
        Calendar per = getNowDate();
        Calendar last = (Calendar) per.clone();

        last.add(Calendar.MINUTE, 60);

        int range = 0;
        range = Calendar.MINUTE;
        setZero(last);
        last.add(range, 30);

        per.add(range, -30 * 5);
        setZero(per);

        while (last.after(per)) {
            int end = (int) (last.getTime().getTime() / 1000);
            String sss = simpleDateFormat.format(last.getTime());
            last.add(range, -30);
            int start = (int) (last.getTime().getTime() / 1000);
            String yyy = simpleDateFormat.format(last.getTime());

            try {
                String name = index + sdf.format(last.getTime()) + getSuffix(last);

                System.out.println(start + "   " + end + "   " + (end - start) + "  "+ yyy + "     " + sss + "   " + name);
            } catch (RangeException e) {
            }
        }
        startDate = (int) (per.getTime().getTime() / 1000);

        System.out.println("startDate：" + index + sdf.format(per.getTime()) + getSuffix(per));
    }

    private void setZero(Calendar c) {
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        int i = c.get(Calendar.MINUTE);
        if (i < 30){
            c.set(Calendar.MINUTE, 0);
        } else {
            c.set(Calendar.MINUTE, 30);
        }
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

    private String getSuffix(Calendar time) {
        int minutes = time.get(Calendar.MINUTE);
        return minutes < 30 ? "-00" : "-30";
    }

    @Test
    public void t() {
        Calendar nowDate = getNowDate();
        int minutes = nowDate.get(Calendar.MINUTE);
        System.out.println(minutes < 30 ? "-00" : "-30");
    }
}
