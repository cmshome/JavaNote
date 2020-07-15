package com.lxk.tool;


import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;


public class TimesUtils {

    public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
            DATE_FORMATTER_MINUTE = new SimpleDateFormat("yyyy-MM-dd HH:mm"),
            DATE_FORMATTER_DATE = new SimpleDateFormat("yyyy-MM-dd");

    public static final int SECOND = 1, MINUTE = 2, HOUR = 3, DAY = 4, MONTH = 5, YEAR = 6;

    public static final int SECOND_IN_MINUTE = 60,
            SECOND_IN_HOUR = SECOND_IN_MINUTE * 60,
            SECOND_IN_30_MINUTE = SECOND_IN_MINUTE * 30,
            SECOND_IN_DAY = SECOND_IN_HOUR * 24,
            SECOND_IN_MONTH = SECOND_IN_DAY * 30,
            SECOND_IN_YEAR = SECOND_IN_MONTH * 12,
            DAYS_IN_WEEK = 7;

    /**
     * 计算两个时间点之间的天数
     */
    public static long getBetweenDay(LocalDate start, LocalDate end) {
        return end.toEpochDay() - start.toEpochDay();
    }

    /**
     * 获取几分钟前的秒数
     *
     * @param minutes
     * @return
     */
    public static String getSecondsMinutesAgo(int minutes) {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.SECOND, 0);
        //当前时间减去2分钟
        ca.add(Calendar.MINUTE, -2);
        ca.add(Calendar.MINUTE, -minutes);
        return "" + ca.getTimeInMillis() / 1000;
    }

    /**
     * 获取当前时间的秒数
     *
     * @return minutes
     */
    public static int getSeconds_current() {
        Calendar ca = Calendar.getInstance();
        String currentTime = "" + ca.getTimeInMillis() / 1000;
        return Integer.parseInt(currentTime);
    }

    /**
     * @return UNIX timestamp(s).
     */
    public static int getUTCTimestampOnSec() {
        return (int) (System.currentTimeMillis() / 1000);
    }


    public static int now() {
        return (int) (Calendar.getInstance().getTimeInMillis() / 1000);
    }

    public static String getFormattedDate(long time) {
        Date date = new Date(time);
        return DATE_FORMATTER.format(date);
    }

    public static String getFormattedDateYMD(long time) {
        Date date = new Date(time);
        return DATE_FORMATTER_DATE.format(date);
    }

    public static Long getZeroTimeInSeconds(long to) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(to * 1000);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
//    	System.out.println(DATE_FORMATTER.format(new Date(cal.getTimeInMillis())));
        Long zero = cal.getTimeInMillis() / 1000;
        return zero;
    }

    /**
     * 获取当前时间之前的时刻
     *
     * @param number 数量
     * @param unit   单位
     * @return
     */
    public static int ago(int number, int unit) {
        int ago = 0, now = now();
        if (unit == HOUR) {
            ago = now - number * 3600;
        } else if (unit == DAY) {
            ago = now - number * 24 * 3600;
        } else if (unit == MINUTE) {
            ago = now - number * 60;
        } else if (unit == SECOND) {
            ago = now - number;
        }
        return ago;
    }

    /**
     * 格式化时间
     *
     * @return
     */
    public static String strftime(Date time, String type) {
        if (time == null) {
            time = new Date();
        }
        SimpleDateFormat sd = new SimpleDateFormat(type);
        return sd.format(time);
    }

    public static long getSecondsOfDay(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time * 1000L);
        return cal.get(Calendar.HOUR_OF_DAY) * TimesUtils.SECOND_IN_HOUR + cal.get(Calendar.MINUTE) * TimesUtils.SECOND_IN_MINUTE + cal.get(Calendar.SECOND);
    }

    /**
     * 获取当前时刻在当天的秒数
     *
     * @return
     */
    public static int getSecondsOfDay(Calendar cal) {
        if (cal == null) {
            cal = Calendar.getInstance();
        }
        return getMinuteOfDay(cal) * 60 + cal.get(Calendar.SECOND);
    }

    /**
     * 获取当前时刻在整分钟分钟数,s 为0
     *
     * @return minute of day.
     */
    public static long getDateOfWholeMinutes() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.SECOND,0);
        return (cal.getTimeInMillis() / 1000L);
    }

    /**
     * 获取当前时刻在当天的分钟数
     *
     * @return minute of day.
     */
    public static int getMinuteOfDay(Calendar cal) {
        if (cal == null) {
            cal = Calendar.getInstance();
        }
        return cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE);
    }

    /**
     * @param datetime datetime(s)
     * @return 获取时间戳的格式化字符串
     */
    //public static String getFormattedDateTimeOnDebug(int datetime) {
    //    Calendar cal = Calendar.getInstance();
    //    cal.setTimeInMillis(datetime * 1000L);
    //    return DateFormatConstant.DATE_TIME_FORMAT.format(cal);
    //}

    /**
     * 获取当天0点的秒数
     */
    public static Long getDayStartSecond() {
        //当日0点开始
        return LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0).atZone(ZoneOffset.systemDefault()).toEpochSecond();
    }

    /**
     * 获取当前时间的秒数
     */
    public static Long getSecondsCurrent() {
        return LocalDateTime.now().atZone(ZoneOffset.systemDefault()).toEpochSecond();
    }

    public static Long getRange(Long selected_time) {
        Long to_date;
        long now = Calendar.getInstance().getTimeInMillis() / 1000 - 2 * TimesUtils.SECOND_IN_MINUTE;
        if (selected_time <= now) {
            if (selected_time + 12 * 60 * 60 <= now) {
                to_date = selected_time + 12 * 60 * 60;
            } else {
                to_date = now;
                if (selected_time + TimesUtils.SECOND_IN_MINUTE > now) {
                    to_date = selected_time + TimesUtils.SECOND_IN_MINUTE;
                }
            }
        } else {
            to_date = selected_time;
        }

        to_date = to_date - to_date % TimesUtils.SECOND_IN_MINUTE;

        return to_date;
    }

    /**
     * 当前时间戳 加/减 n天之后的时间戳
     *
     * @param second 时间，秒数
     * @param day    天数
     * @return 时间戳
     */
    static Long plusDays(Long second, int day, ZoneId zoneId) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(second), zoneId).plusDays(day).atZone(zoneId).toEpochSecond();
    }
}
