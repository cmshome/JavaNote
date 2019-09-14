package com.lxk.jdk8.date;

import com.lxk.tool.time.TimesUtils;
import org.junit.Test;

import java.time.LocalDate;

/**
 * 算娃出生多少天了
 *
 * @author LiXuekai on 2019/9/10
 */
public class GetBetweenDaysTest {

    /**
     * 计算两个时间点之间的天数
     */
    @Test
    public void getBetweenDay() {
        //阴历，腊月27早上出生🐣，28，29，30。距离过年三天
        LocalDate start = LocalDate.of(2018, 2, 12);
        LocalDate now = LocalDate.now();
        System.out.println("儿子今天是 " + TimesUtils.getBetweenDay(start, now) + " 天啦。");
    }
}
