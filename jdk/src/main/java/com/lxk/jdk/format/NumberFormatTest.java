package com.lxk.jdk.format;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 数字格式化测试
 *
 * @author lxk on 2017/1/22
 */
public class NumberFormatTest {

    /**
     * 获得8位数的长度值 将msg的字节长度转成8位数
     */
    @Test
    @SuppressWarnings("unchecked")
    public void get8BitLength() {
        String length = "123456789";
        int expectLength = 8;
        int count = length.length();
        int needAdd = expectLength - count;
        StringBuilder stringBuilder = new StringBuilder();
        List<String> list = Lists.newArrayList();
        if (needAdd > 0) {
            list = new ArrayList(Collections.nCopies(needAdd, "0"));
        }
        if (!list.isEmpty()) {
            list.forEach(stringBuilder::append);
        }
        System.out.println(stringBuilder.toString() + length);
    }

    /**
     * 2.3F经过格式化，竟然变成2.99啦。what the fuck .
     * float类型的时候，值是2.3，但是一经变成Double，值就变成2.99999啦。
     * 这个format的参数是double类型的。所以，在传入参数的时候，就变成了对2.299999952316284进行操作。返回就过就是2.99
     */
    @Test
    public void floatFormatTest() {
        float responseTime = 2.3F;
        NumberFormat formatter;
        formatter = NumberFormat.getNumberInstance();
        formatter.setGroupingUsed(false);
        formatter.setRoundingMode(RoundingMode.FLOOR);
        formatter.setMaximumFractionDigits(2);
        String format = formatter.format(responseTime);
        System.out.println(format);
        BigDecimal bigDecimal = new BigDecimal(responseTime + "");
        float v = Float.parseFloat(formatter.format(bigDecimal));
        System.out.println(v);
    }

    /**
     * 数字字符串要是带空格就会转BigDecimal失败。
     */
    @Test
    public void bigDecimalTest() {
        BigDecimal bigDecimal = new BigDecimal(11.12121212121);
        //11.12121212121000013439697795547544956207275390625
        System.out.println(bigDecimal.toPlainString());
        bigDecimal = new BigDecimal("11.12121212121        ".trim());
        //11.12121212121
        System.out.println(bigDecimal.toPlainString());

    }

    /**
     * 科学计数法的还原
     */
    @Test
    public void scientificNumber() {
        long ll = 1234567845134321L;
        System.out.println(ll);
        double bigValue = 6.21482E+18D;
        System.out.println(bigValue);

        DecimalFormat df = new DecimalFormat("0");
        System.out.println(df.format(bigValue));

        double num2 = 50123.12;
        System.out.println(num2);
        BigDecimal bd2 = new BigDecimal(num2);
        System.out.println(bd2.toPlainString());
        System.out.println(df.format(num2));

        BigDecimal bd = new BigDecimal("1.1920928955078125e-7");
        String str = bd.toPlainString();
        System.out.println(str);

        BigDecimal bds = new BigDecimal("5.1623245E7");
        String strs = bds.toPlainString();
        System.out.println(strs);

        String sss = "12344.33";
        System.out.println(sss.contains("."));

    }

    @Test
    public void beforeTestGroup() {
        Float result;
        //result = 9.313226E-10,也就是0.0000000009313226
        //result = ((float) 1L) / (1024L * 1024L * 1024L);
        //result = 1230.5F;
        result = 1230.55F;
        //result = 1230.466F;
        System.out.println("-------------我是分界线-------------");
        showFormat(result);
        //result = 10.5F;
        //result = 10.4F;
        result = 10.6F;
        System.out.println("-------------我是分界线-------------");
        showFormatMore(result);
    }

    /**
     * 测试各种舍入模式的不同
     *
     * @param result 待格式化的参数
     */
    private void showFormatMore(Float result) {
        System.out.println("初始值：" + result.toString());
        DecimalFormat decimalFormat = new DecimalFormat("0");
        decimalFormat.setRoundingMode(RoundingMode.HALF_EVEN);
        System.out.println("HALF_EVEN：" + decimalFormat.format(result));
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        System.out.println("HALF_UP：" + decimalFormat.format(result));
        decimalFormat.setRoundingMode(RoundingMode.HALF_DOWN);
        System.out.println("HALF_DOWN：" + decimalFormat.format(result));
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);
        System.out.println("FLOOR：" + decimalFormat.format(result));
        decimalFormat.setRoundingMode(RoundingMode.CEILING);
        System.out.println("CEILING：" + decimalFormat.format(result));
        decimalFormat.setRoundingMode(RoundingMode.UP);
        System.out.println("UP：" + decimalFormat.format(result));
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        System.out.println("DOWN：" + decimalFormat.format(result));
    }

    /**
     * 打印数据遭格式化之后的效果(默认是HALF_EVEN，他不是四舍五入，)
     *
     * @param value 待格式化的参数
     */
    private void showFormat(Float value) {
        System.out.println("初始值：" + value.toString());
        //取一位整数
        System.out.println(new DecimalFormat("0").format(value));
        //取一位整数和两位小数
        System.out.println(new DecimalFormat("0.00").format(value));
        //取两位整数和三位小数，整数不足部分以0填补
        System.out.println(new DecimalFormat("00.000").format(value));
        //取所有整数部分
        System.out.println(new DecimalFormat("#").format(value));
        //以百分比方式计数，并取两位小数
        System.out.println(new DecimalFormat("#.##%").format(value));
        //显示为科学计数法，并取五位小数
        System.out.println(new DecimalFormat("#.#####E0").format(value));
        //显示为两位整数的科学计数法，并取四位小数
        System.out.println(new DecimalFormat("00.####E0").format(value));
        //每三位以逗号进行分隔。
        System.out.println(new DecimalFormat(",###").format(value));
        //将格式嵌入文本
        System.out.println(new DecimalFormat("所传入的格式化参数是：###大小。").format(value));
    }

    @Test
    public void format() {
        double value = 987654321100.0023;
        System.out.println(new DecimalFormat("#.##%").format(value));
        System.out.println(new DecimalFormat("0.00").format(new BigDecimal("987654321100.0023")));

    }



}
