package com.lxk.tool;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 时间相关的工具。
 *
 * @author LiXuekai on 2021/1/27
 */
public class TimeUtils {
    private static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss:SSS";
    private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    private static final String EMPTY = "";
    private static final long K = 1000L;
    private static final int ZERO = 0;
    private static final DateTimeFormatter DATE_TIME_FORMATTER_SSS = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS_SSS);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);
    private static final ZoneId ZONE_ID = ZoneOffset.systemDefault();


    /**
     * 获取格式化后的时间
     *
     * @return 格式化后的时间
     */
    public static String now() {
        return format(LocalDateTime.now());
    }

    /**
     * 获取当前时间的秒数
     *
     * @return 当前时间戳 秒
     */
    public static long nowS() {
        return nowMs() / K;
    }

    /**
     * 获取当前时间的毫秒数
     *
     * @return 当前时间戳毫秒
     */
    public static long nowMs() {
        return System.currentTimeMillis();
    }

    /**
     * 默认格式化时间格式为：yyyy-MM-dd HH:mm:ss
     *
     * @param localDateTime localDateTime
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String format(LocalDateTime localDateTime) {
        return DATE_TIME_FORMATTER.format(localDateTime);
    }

    /**
     * 格式化时间戳
     *
     * @param s 秒
     * @return 格式化后的时间
     */
    public static String formatS(long s) {
        try {
            LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(s * K), ZONE_ID);
            return DATE_TIME_FORMATTER.format(localDateTime);
        } catch (Exception ignore) {
        }
        return EMPTY;
    }

    /**
     * 格式化时间戳
     *
     * @param ms 毫秒
     * @return 格式化后的时间
     */
    public static String formatMs(long ms) {
        try {
            LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(ms), ZONE_ID);
            return DATE_TIME_FORMATTER_SSS.format(localDateTime);
        } catch (Exception ignore) {
        }
        return EMPTY;
    }

    /**
     * 秒 -> LocalDateTime
     *
     * @param s 秒
     * @return LocalDateTime
     */
    public static LocalDateTime castSecondsToLocalDateTime(long s) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(s), ZONE_ID);
    }

    /**
     * LocalDateTime -> 秒
     *
     * @param localDateTime localDateTime
     * @return 秒
     */
    public static long castLocalDateTimeToSeconds(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZONE_ID).toEpochSecond();
    }

    /**
     * Date -> LocalDateTime
     *
     * @param date date
     * @return localDateTime
     */
    public static LocalDateTime catsDateToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZONE_ID).toLocalDateTime();
    }

    /**
     * 当天0点的秒
     *
     * @return 当前0点的时间戳秒
     */
    public static Long dayStartSecond() {
        return dayStartSecond(LocalDateTime.now());
    }

    /**
     * 置传入时间为整0点时间戳秒
     *
     * @param localDateTime LocalDateTime
     * @return 传入时间置为整0点时间戳秒
     */
    public static Long dayStartSecond(LocalDateTime localDateTime) {
        return localDateTime.withHour(ZERO).withMinute(ZERO).withSecond(ZERO).withNano(ZERO).atZone(ZONE_ID).toEpochSecond();
    }

    /**
     * 计算两个时间点之间的天数
     *
     * @return 2个时间间隔的天数
     */
    public static long betweenDay(LocalDate start, LocalDate end) {
        return end.toEpochDay() - start.toEpochDay();
    }

    /**
     * 当前时间戳 加/减 n天之后的时间戳
     *
     * @param second 时间，秒数
     * @param day    天数
     * @return 时间戳
     */
    static Long plusDays(Long second, int day) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(second), ZONE_ID).plusDays(day).atZone(ZONE_ID).toEpochSecond();
    }
}
