package com.lxk.jdk.format;


import com.lxk.tool.time.TimesUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期格式化测试
 *
 * @author lxk on 2016/11/4
 */
public class DateFormat {

    /**
     * 格式化输出时间 _2019-07-18_15
     */
    @Test
    public void formatTime() {
        String formatString = "_yyyy-MM-dd_HH";
        SimpleDateFormat sdf = new SimpleDateFormat(formatString);
        String dateString = sdf.format(new Date());
        //_2019-07-18_15
        System.out.println(dateString);
    }

    @Test
    public void formatDataTest() {
        /*
         * 日期转期望格式的字符串
         */
        //HH 和 hh 的差别：前者是24小时制，后者是12小时制。
        String formatString = "yyyy年MM月dd日 HH:mm:ss" +
                " 上下午标志 a" +
                " E" +
                " 一年中的第D天" +
                " 一月中的第F个星期" +
                " 一年中的第w个星期" +
                " 一月中的第W个星期" +
                " Z" +
                " z";
        SimpleDateFormat sdf = new SimpleDateFormat(formatString);
        String dateString = sdf.format(new Date());
        System.out.println(dateString);
        /*
         * 字符串转日期
         */
        Date date;
        try {
            date = sdf.parse(dateString);
            System.out.println(date);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void noGroup() {
        Long dayStartSecond = TimesUtils.getDayStartSecond();
        long nowDate = Calendar.getInstance().getTimeInMillis();
        //result.put("date", nowDate);//服务器时间 1478793600
        long minute = (nowDate / 1000 - dayStartSecond) / 60;

        Date s = new Date();
        //System.out.println(minute);

        String date = "2016-12-07T16:00:00.000Z";
        //注意在 UTC 字符串前面还有个空格。不然异常。
        date = date.replace("Z", " UTC");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        try {
            Date d = sdf.parse(date);
            System.out.println("Z日期: " + d);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //Calendar aw = Calendar.getInstance();//获得时间的另一种方式，测试效果一样
        //System.out.println(aw.getTime().getTime());
        //BigDecimal bd = new BigDecimal(11.11922);
        //1478656368669
        //1478793600000
        //1478793600000
        //Double totalBytes = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        //System.out.println(totalBytes);
    }

    /**
     * SimpleDateFormat 格式化日期
     * 日期 - >  格式化过的字符串
     */
    @Test
    public void dateTest() {
        Date ss = new Date();
        System.out.println("一般日期输出：" + ss);
        System.out.println("时间戳 获得的是毫秒：" + ss.getTime());
        //Date aw = Calendar.getInstance().getTime();//获得时间的另一种方式，测试效果一样
        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //这个就是把时间戳经过处理得到期望格式的时间
        String time = format0.format(ss.getTime());
        System.out.println("格式化结果0：" + time);

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        time = format1.format(ss.getTime());
        System.out.println("格式化结果1：" + time);
    }

}
