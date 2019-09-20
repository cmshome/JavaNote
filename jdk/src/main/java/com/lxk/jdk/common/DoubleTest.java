package com.lxk.jdk.common;

import com.google.common.collect.Lists;
import com.lxk.tool.number.DoubleUtil;
import com.lxk.tool.print.PrintUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * float和double只能用来做科学计算或者是工程计算.
 * 在商业计算中我们要用 java.math.BigDecimal
 *
 * @author lxk on 2017/9/27
 */
public class DoubleTest {


    @Test
    public void test() {
        Double add = DoubleUtil.add(1.11, 2.22);
        System.out.println(add);
    }

    /**
     * 测试值为 infinity（无穷 ∞ ） 的情况
     */
    @Test
    public void testInfinity() {
        Double ss = 1.0D / 0.0D;
        System.out.println(Double.isInfinite(ss));
        System.out.println(Double.isFinite(ss));

        System.out.println("-----");


        System.out.println(ss);
        System.out.println(ss.doubleValue());

        Double yy = 0.0D / 0.0D;
        System.out.println(yy);
        //0.0d / 0.0
        System.out.println(Double.NaN);
        //-1.0 / 0.0
        System.out.println(Double.NEGATIVE_INFINITY);
        //1.0 / 0.0
        System.out.println(Double.POSITIVE_INFINITY);

        if (ss.isInfinite()) {
            return;
        }

        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setGroupingUsed(false);
        formatter.setRoundingMode(RoundingMode.HALF_UP);
        formatter.setMaximumFractionDigits(2);
        BigDecimal bigDecimal = new BigDecimal(ss + "");

        System.out.println(formatter.format(bigDecimal));

    }

    @Test
    public void testAdd() {
        Number number = 12.1233;
        System.out.println(number.longValue());
        System.out.println(number.intValue());
        Double d1 = 1D;
        Double d2 = 1.22D;
        System.out.println(d1 + d2);
        System.out.println(DoubleUtil.add(d1, d2));
        String count = "100";
        Long totalCount = 200L;
        Double label = Double.parseDouble(count) / totalCount;
        System.out.println(label);
        System.out.println(Lists.newArrayList("ssssss".split(",")));

        //空集合，直接get 0 会 数组下标越界
        //List<String> a = Lists.newArrayList();
        //a.get(0);
    }


    /**
     * 测试结果不是很理想啊。
     */
    @Test
    public void testCompare() {
        Double d = 100D;
        //这个时候，运行结果，都是0，
        //Double d2 = 100.00000000000000001D;
        //这个时候，就能对比出来谁大谁小啦。
        Double d2 = 100.0001D;

        System.out.println(0.1D > 0);
        // 0
        System.out.println(d.compareTo(d2));
        System.out.println(d2.compareTo(d));
        BigDecimal b1 = new BigDecimal(d + "");
        BigDecimal b2 = new BigDecimal(d2 + "");
        // 0
        System.out.println(b1.compareTo(b2));
        // 0
        System.out.println(b2.compareTo(b1));
        System.out.println();
    }


    /**
     * BigDecimal的测试，要精确。
     * 还要使得科学计数法的数字，做完全的展示。
     */
    @Test
    public void testBigDecimal() {
        Double d = 1.6D;
        //不准确的初始化
        BigDecimal bigDecimal = new BigDecimal(d);
        System.out.println(bigDecimal);

        //使得结果精确的初始化姿势
        bigDecimal = new BigDecimal(d.toString());
        System.out.println(bigDecimal);

        bigDecimal = new BigDecimal("6.214822313132341212666E+18");
        System.out.println(bigDecimal.toPlainString());
    }

    /**
     * double的一些计算奇葩现象，试验一把，就印象深刻啦。
     */
    @Test
    public void testDouble() {
        Double d = 0.81d;
        System.out.println(d);
        PrintUtil.divideLine();
        //0.060000000000000005
        System.out.println("0.05 + 0.01 = " + (0.05 + 0.01));
        //0.5800000000000001
        System.out.println("1.0 - 0.42 = " + (1.0 - 0.42));
        //401.49999999999994
        System.out.println("4.015 * 100 = " + (4.015 * 100));
        //1.2329999999999999
        System.out.println("123.3 / 100 = " + (123.3 / 100));
        //4.03 四舍五入
        System.out.println(new DecimalFormat("0.00").format(4.025d));
    }

    /**
     * 精确计算
     */
    @Test
    public void testDoubleExact() {
        System.out.println("0.05 + 0.01 = " + DoubleUtil.add(0.05, 0.01));
        System.out.println("1.0 - 0.42 = " + DoubleUtil.sub(1.0, 0.42));
        System.out.println("4.015 * 100 = " + DoubleUtil.mul(4.015, 100d));
        //保留两位
        System.out.println("123.3 / 100 = " + DoubleUtil.divide(123.3, 100d));
        //保留三位
        System.out.println("123.3 / 100 = " + DoubleUtil.divide(123.3, 100d, 3));
        System.out.println(DoubleUtil.round(4.025d, 2));
    }
}
