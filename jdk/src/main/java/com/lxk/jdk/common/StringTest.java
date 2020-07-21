package com.lxk.jdk.common;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * String字符串测试
 *
 * @author lxk on 2017/2/8
 */
public class StringTest {
    private static final String HEL = "Hel";
    private static final String LO = "lo";
    private static final String HEL_;
    private static final String LO_;

    private static final char DOUBLE_QUOTATION_MARKS = '"';
    private static final char SINGLE_QUOTATION_MARK = '\'';
    private static final String TTM = "0|probeName_lxk|188.188.8.118|10999|10.188.16.110|40998|grcb8583|{\"number\":\"78\",\"convers_id\": 12864829, \"msg_size\": 876, \"mti\": \"\", \"STAN\": \"  3133\", \"F38\": \"交易成功\", \"RC\": \"0000\", \"F41\": \"文件(PHPFG0),业务种类(000)\", \"mid\": \"\"}|||1|876|0|0|0|0|0|8|0|0|0|0|0|0|0|0|0|0|1536646259|1536646259923|2018-09-11T06:10:59|3166439542|180097134|6|0|7";

    static {
        HEL_ = "Hel";
        LO_ = "lo";
    }

    /**
     * 点做分隔符的时候，需要转义一下，不能直接split
     */
    @Test
    public void splitpoint(){
        String s = "trans_ref.amount";
        String[] split = s.split("\\.");
        System.out.println(Arrays.toString(split));
        System.out.println(s.contains("\\."));
    }

    /**
     * replace方法，如果arg1不存在，那么原始值是不会被修改的。
     */
    @Test
    public void replaceTest(){
        String s = "trans_ref.amount";
        System.out.println(s.replace("lxk",""));
    }

    @Test
    public void subStringTest(){
        String s = "12345678qwweqwrqqtqtqt";
        System.out.println(s.substring(8));
    }
    @Test
    public void lengthTest(){
        String ss = "我是一个小学生";
        System.out.println(ss.length());
        byte[] bytes = ss.getBytes(StandardCharsets.UTF_8);
        System.out.println(Arrays.toString(bytes));
        System.out.println(bytes.length);
    }

    @Test
    public void java8() {
        String[] split = "link:\"10.0.14.133:19291-10.0.2.87:10041\"".split(":");
        Arrays.stream(split).filter(s1 -> !"OR".equals(s1)).forEach(System.out::println);
        Arrays.stream("sda.sda".split("\\.")).forEach(System.out::println);
    }

    /**
     * 测试字符串分割的效率问题 StringTokenizer 和 split 的对比。
     */
    @Test
    public void testSplitEfficiency() {
        while (true) {
            //29.4%
            split();
            //70.5%
            stringTokenizer();
        }
    }

    private static void stringTokenizer() {
        StringTokenizer st = new StringTokenizer(TTM, "\\|");
        StringBuilder stringBuilder = new StringBuilder();
        while (st.hasMoreTokens()) {
            stringBuilder.append(st.nextToken());
        }
        String s = stringBuilder.toString();
    }

    private static void split() {
        String[] split = TTM.split("\\|");
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : split) {
            stringBuilder.append(s);
        }
        String s = stringBuilder.toString();
    }

    @Test
    public void testStringContact() {
        String[] split = TTM.split("\\|");
        int length = split.length;
        while (true) {
            //56%
            String s = testAdd(split, length);
            //13.8%
            String s1 = testStringBuilderAppend(split, length);
            //14.4%
            String s2 = testStringBufferAppend(split, length);
            //15.8%
            String s3 = testContact(split, length);
        }
    }

    private static String testContact(String[] split, int length) {
        String result = "";
        for (int i = 0; i < length; i++) {
            result = result.concat(split[i]);
        }
        return result;
    }

    private static String testStringBufferAppend(String[] split, int length) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            stringBuffer.append(split[i]);
        }
        return stringBuffer.toString();
    }

    private static String testStringBuilderAppend(String[] split, int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(split[i]);
        }
        return stringBuilder.toString();
    }

    private static String testAdd(String[] split, int length) {
        String result = null;
        for (int i = 0; i < length; i++) {
            result += split[i];
        }
        return result;
    }

    @Test
    public void test() {
        while (true) {
            test1();
            test2();
        }
    }

    private void test2() {
        String[] split = TTM.split("\\|");
        int length = split.length;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(split[i]);
        }
    }

    private void test1() {
        StringTokenizer stringTokenizer = new StringTokenizer(TTM, "\\|");
        StringBuilder stringBuilder = new StringBuilder();
        while (stringTokenizer.hasMoreElements()) {
            stringBuilder.append(stringTokenizer.nextToken());
        }
    }

    /**
     * 测试下面三个处理字符串的效率问题
     * cleanString ：cleanString2：cleanString3 = 32.9 : 23.9 : 30.2
     * 可见 cleanString2 效率高。
     */
    @Test
    public void testEfficiency() {
        while (true) {
            testCleanString();
            testCleanString2();
            testCleanString3();
        }
    }

    private static void testCleanString() {
        String s = "\"1234\"";
        cleanString(s);

        s = "'1234'";
        cleanString(s);

        s = "1234";
        cleanString(s);

        s = "123123'";
        cleanString(s);

        s = "12315623\'";
        cleanString(s);

        s = "123123\"\"";
        cleanString(s);
    }

    private static void testCleanString2() {
        String s = "\"1234\"";
        cleanString2(s);

        s = "'1234'";
        cleanString2(s);

        s = "1234";
        cleanString2(s);

        s = "123123'";
        cleanString2(s);

        s = "12315623\'";
        cleanString2(s);

        s = "123123\"\"";
        cleanString2(s);
    }

    private static void testCleanString3() {
        String s = "\"1234\"";
        cleanString3(s);

        s = "'1234'";
        cleanString3(s);

        s = "1234";
        cleanString3(s);

        s = "123123'";
        cleanString3(s);

        s = "12315623\'";
        cleanString3(s);

        s = "123123\"\"";
        cleanString3(s);
    }

    private static String cleanString3(String value) {
        if ((value == null) || (value.isEmpty())) {
            return null;
        }
        if ((value.startsWith("\"") && value.endsWith("\""))
                || (value.startsWith("'") && value.endsWith("'"))) {
            value = value.substring(1, value.length() - 1);
        }
        return value;
    }

    /**
     * 相比下面的，判断次数是减少了，效率提高啦。
     * 字符串如果包含了""或者''，去掉最外层的引号。
     *
     * @param value 值
     * @return 字符串值
     */
    private static String cleanString2(String value) {
        if ((value == null) || (value.isEmpty())) {
            return null;
        }
        char firstChar = value.charAt(0);
        if (firstChar == DOUBLE_QUOTATION_MARKS) {
            int lastIndex = value.length() - 1;
            char lastChar = value.charAt(lastIndex);
            if (lastChar == DOUBLE_QUOTATION_MARKS) {
                return value.substring(1, lastIndex);
            }
        } else if (firstChar == SINGLE_QUOTATION_MARK) {
            int lastIndex = value.length() - 1;
            char lastChar = value.charAt(lastIndex);
            if (lastChar == SINGLE_QUOTATION_MARK) {
                return value.substring(1, lastIndex);
            }
        }
        return value;
    }

    /**
     * 字符串如果包含了""或者''，去掉最外层的引号。
     *
     * @param value 值
     * @return 字符串值
     */
    private static String cleanString(String value) {
        if ((value == null) || (value.isEmpty())) {
            return null;
        }
        int length = value.length();
        int lastIndex = length - 1;
        char firstChar = value.charAt(0);
        char lastChar = value.charAt(lastIndex);
        //是被双引号引住的字符串
        boolean containDouble = firstChar == '"' && lastChar == '"';
        //是被单引号引住的字符串
        boolean containSingle = firstChar == '\'' && lastChar == '\'';
        if (containDouble || containSingle) {
            value = value.substring(1, lastIndex);
        }
        return value;
    }

    static List<String> METRIC_IN_VISUAL_QUERY_STRING =
            new ImmutableList.Builder<String>()

                    .add("src_ip")
                    .add("sport")
                    .add("dst_ip")
                    .add("dport")
                    .add("trans_id")
                    .add("probe_name")
                    .add("link")
                    .add("src_ip_province")
                    .add("latency_msec")
                    .add("rtt")
                    .add("trans_ref")
                    .add("ret_code")

                    .build();

    private static String adapterQueryString(String queryString) {
        if (Strings.isNullOrEmpty(queryString)) {
            return queryString;
        }
        String splitString = " ";
        String prefix = "_";
        List<String> notMetricList = Lists.newArrayList("AND", "OR", "NOT");
        //准备删除的旧数据
        List<String> remove = Lists.newArrayList();

        String[] split = queryString.split(splitString);
        for (int i = 0; i < split.length; i++) {
            //value可能是配置的值或者连接符（AND、OR、NOT）
            String value = split[i];
            if (Strings.isNullOrEmpty(value)) {
                continue;
            }
            //连接符
            if (notMetricList.contains(value)) {
                continue;
            }
            //不管指标怎么样，都给去掉开头的下划线
            if (value.startsWith(prefix)) {
                value = value.replaceFirst(prefix, "");
            }

            //下面考虑删除掉4.1不存在的指标.
            String[] split1 = value.split(":");
            String metric = split1[0];


            if (metric.contains(".")) {
                metric = metric.split("\\.")[0];
            }
            //既不是4.1的指标，也不是连接符。
            if (!METRIC_IN_VISUAL_QUERY_STRING.contains(metric)) {
                if (i - 1 > -1) {
                    String before = split[i - 1];
                    remove.add(" " + before + " " + value);
                } else {
                    remove.add(value);
                }
            }
            //更新数组的值
            split[i] = value;
        }

        Joiner joiner = Joiner.on(" ").skipNulls();
        queryString = joiner.join(split);

        for (String s : remove) {
            queryString = queryString.replace(s, "");
        }

        List<String> special = Lists.newArrayList(" OR ", " NOT ", " AND ");
        for (String s : special) {
            if (queryString.startsWith(s)) {
                queryString = queryString.replaceFirst(s, "");
            }
        }

        return queryString;
    }

    @Test
    public void testReplace() {
        String s = "_protocol:6 OR _link:\"10.0.14.133:19291-10.0.2.87:10041\" OR _probe_name:PROBE_ETH0_1 OR _trans_ref.TT:09003000005 OR _ret_code.ProcessState:OK OR _latency_msec:22 OR _trans_transfer_ms:24 OR _out_bytes:2283 OR _in_bytes:2426 OR _out_pkts:22 OR _in_pkts OR _rtt:0 OR _tot_syn:0 OR _tot_fin_s:3 OR _tot_fin:2 OR _tot_synack:0 OR _tot_rst:1 OR _tot_rst_s:1 OR _out_ooo:0 OR _out_retran:46 OR _in_retran:2 OR _tot_zero_client:0 OR _tot_zero_server:0 OR _in_ooo:0";

        List<String> list = Lists.newArrayList();
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        String string = adapterQueryString(s);
        System.out.println(s);
        System.out.println(string);

        //String[] split = s.split(" ");
        //Arrays.stream(split).filter(s1 -> !"OR".equals(s1)).forEach(System.out::println);
        //for (int i = 0; i < split.length; i++) {
        //    String s1 = split[i];
        //    if (s1.startsWith("_")) {
        //        s1 = s1.replaceFirst("_", "");
        //        split[i] = s1;
        //    }
        //}
        //System.out.println();
        //Arrays.stream(split).filter(s1 -> !"OR".equals(s1)).forEach(System.out::println);
        //Joiner joiner = Joiner.on(" ").skipNulls();
        //String join = joiner.join(split);
        //
        //System.out.println(s);
        //System.out.println(join);

    }

    /**
     * 测试经典的字符串常量池问题。
     */
    @Test
    public void testAddress() {
        String s1 = "Hello";
        String s2 = "Hello";
        String s3 = "Hel" + "lo";
        String s4 = "Hel" + new String("lo");
        String s5 = new String("Hello");
        String s6 = s5.intern();
        String s7 = "H";
        String s8 = "ello";
        String s9 = s7 + s8;

        // true 都是字面量，编译期间就可以确定值
        System.out.println(s1 == s2);
        // true 同理，字面量，编译期间可以确定值
        System.out.println(s1 == s3);
        // false 但new String("lo")这部分不是已知字面量，是一个不可预料的部分，编译器不会优化，必须等到运行时才可以确定结果，
        System.out.println(s1 == s4);
        // false  虽然s7、s8在赋值的时候使用的字符串字面量，但是拼接成s9的时候，s7、s8作为两个变量，都是不可预料的
        System.out.println(s1 == s9);
        // false 都是new出来的，肯定不会相等的
        System.out.println(s4 == s5);
        // true  ，intern方法会尝试将Hello字符串添加到常量池中，并返回其在常量池中的地址
        System.out.println(s1 == s6);

        String s10 = HEL + LO;
        // true s10是虽然是加出来的，但是a0和a1都是static final 编译期间，就已经知道啦。所以，s10，也是常量。
        System.out.println(s1 == s10);

        String s11 = HEL_ + LO_;
        // false s11也是加出来的，但是后面的2个是通过静态代码块赋值的，静态代码块，只有在类加载的时候，才执行。所以，后面的2个是不确定值。
        System.out.println(s1 == s11);


    }

    /**
     * 测试：trim返回新的字符串，不修改原来的字符串。
     */
    @Test
    public void testTrim() {
        String s = "     123     ";
        System.out.println(s.trim());
        System.out.println(s);
    }

    /**
     * 可变参数的测试
     * 可变参数的定义，必须是放在参数列表的最后面
     */
    @Test
    public void testManyArgs() {
        int d = 1;
        System.out.println(isNotNullOrEmpty(d, "a"));
        System.out.println(isNotNullOrEmpty(d, "a", "b"));
        System.out.println(isNotNullOrEmpty(d, "a", "b", ""));
        String str = concatString(new String[]{"a", "b", "c", "d"});
        System.out.println(str);
    }

    private static boolean isNotNullOrEmpty(int d, String... arg) {
        System.out.println(d);
        for (String s : arg) {
            if (Strings.isNullOrEmpty(s)) {
                return false;
            }
        }
        return true;
    }

    public static String concatString(String... strings) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            str.append(strings[i]);
            if (i != strings.length - 1) {
                str.append("--");
            }
        }
        return str.toString();
    }

    /**
     * intern方法测试，讲字符串加入到常量池 native 方法。
     * s1 s2 声明完之后，就在常量池啦，因为这个常量声明方法。
     * 再intern方法，他已经存在啦，此方法就把池里面的返回啦。
     * 所以，比较地址，就是自己跟自己比，就相等啦。
     */
    @Test
    public void testStringIntern() {
        String s1 = "go" + "od";
        String s2 = "ja" + "va";
        //true
        System.out.println(s1.intern() == s1);
        //true
        System.out.println(s2.intern() == s2);
    }

    /**
     * 测试一个新建字符串的不常见姿势
     */
    @Test
    public void testNewStringArray() {
        String[] command = new String[]{"sh", "-c", "ps -ef | grep \""
                + "d:test" + "\" | grep \"" + "d:test" + "\" | grep -v \"grep\" | awk '{ print $2}'"};
        System.out.println(Arrays.toString(command));
        //结果如下：
        //[sh, -c, ps -ef | grep "d:test" | grep "d:test" | grep -v "grep" | awk '{ print $2}']
    }

    /**
     * 使用indexOf()来拆分字符串：D:\Android\sdk\add-ons
     */
    @Test
    public void testIndexOf() {
        String string = "aaa456ac";

        //查找指定字符是在字符串中的下标。在则返回所在字符串下标；不在则返回-1.
        //indexOf(String str)；返回结果：-1，"b"不存在
        System.out.println(string.indexOf("b"));

        // 从第四个字符位置开始往后继续查找，包含当前位置
        //indexOf(String str, int fromIndex)；返回结果：6
        System.out.println(string.indexOf("a", 3));

        //（与之前的差别：上面的参数是 String 类型，下面的参数是 int 类型）参考数据：a-97,b-98,c-99

        // 从头开始查找是否存在指定的字符
        //indexOf(int ch)；返回结果：7
        System.out.println(string.indexOf(99));
        //indexOf(int ch)；返回结果：7
        System.out.println(string.indexOf('c'));

        //从fromIndex查找ch，这个是字符型变量，不是字符串。字符a对应的数字就是97。
        //indexOf(int ch, int fromIndex)；返回结果：6
        System.out.println(string.indexOf(97, 3));
        //indexOf(int ch, int fromIndex)；返回结果：6
        System.out.println(string.indexOf('a', 3));

        //这个就是灵活运用String类提供的方法，拆分提供的字符串。
        String s = "D:\\Android\\sdk\\add-ons";
        System.out.println(s);
        while (s.lastIndexOf("\\") > 0) {
            s = s.substring(0, s.lastIndexOf("\\"));
            System.out.println(s);
        }
    }

    /**
     * 将字符串倒序
     */
    @Test
    public void testReverseString() {
        String string = "please call me big brother";
        char[] chars = string.toCharArray();
        int length = chars.length;
        for (int i = 0; i < length / 2; i++) {
            char temp;
            temp = chars[i];
            chars[i] = chars[length - 1 - i];
            chars[length - 1 - i] = temp;
        }
        System.out.println(chars);
    }

    /**
     * 将list的内容以逗号间隔，最后不应该多个逗号。
     */
    @Test
    public void testListToString() {
        List<String> s = Lists.newArrayList("1", "2", "3");
        StringBuilder sb = new StringBuilder();
        for (String s1 : s) {
            sb.append(s1).append(",");
        }
        System.out.println(sb.toString().substring(0, sb.lastIndexOf(",")));
        System.out.println("等效的快捷方式");
        //跳过null
        Joiner joiner = Joiner.on(",").skipNulls();
        //System.out.println(joiner.join(s));
        System.out.println(joiner.join(s.toArray()));
    }

    /**
     * 测试string转换成char类型
     */
    @Test
    public void testStringToChar() {
        String s = "abcdefghi";
        char result[] = s.toCharArray();
        char ss = s.toCharArray()[0];
        System.out.println(ss);
        System.out.println(result);
        char char0 = s.charAt(0);
        System.out.println(char0);
    }

    /**
     * 还是测试字符串 ==
     */
    @Test
    public void testStringPool2() {
        String s0 = "ab";
        String s1 = "a";
        String s3 = "a" + "b";
        String s2 = s1 + "b";
        System.out.println(s0 == s3);
        System.out.println(s2 == s3);
    }

    @Test
    public void testSubstring() {
        String ss = "-1,-0";
        String[] split = ss.split(",");
        String split1 = split[0];
        String split2 = split[1];
        String data = "0123456789876543210";

        int length = data.toString().length();
        String value = null;
        try {
            int from = Integer.parseInt(split1);
            if (from < 0) {
                from = length + from;
            }
            int to = Integer.parseInt(split2);
            if (split2.startsWith("-")) {
                to = to + length;
            }
            value = data.toString().substring(from, to);
            System.out.println(value);
        } catch (Exception e) {
        }



    }
    /**
     * 测试字符串的 split()
     * 返回的数组，若末尾有一连串空的，则舍弃
     */
    @Test
    public void testSplit() {
        String ss = ",aa,bb,cc,dd,,,";
        ss = "1|2|3||7|4";
        String[] array = ss.split("\\|");
        System.out.println(Arrays.toString(array));

        //结果是5，而不是预想中的8
        System.out.println(array.length);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    /**
     * 字符串 split()新发现
     */
    @Test
    public void testSplitPlus() {
        String ss = "aa12sas32sasa223sas12as12wqe";
        String[] array = ss.split("[\\d]+");
        System.out.println(Arrays.toString(array));
        ss = "aa,,sas,,sasa,,,,sasas,,,";
        array = ss.split("[,]+");
        System.out.println(Arrays.toString(array));
        ss = "aa  sas sa sa     sas  as  ";
        array = ss.split("[\\s]+");
        System.out.println(Arrays.toString(array));
    }

    /**
     * 测试代码中的换行："\r\n"和<br>
     */
    @Test
    public void testStringNewLine() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            sb.append("大").append("\r\n");
        }
        System.out.println(sb.toString());
    }

    /**
     * StringBuffer和StringBuilder的使用
     */
    @Test
    public void testStringBufferAndStringBuilder() {
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sbf.append("这是第").append(i).append("个; ");
        }
        System.out.println(sbf);
        System.out.println(sbf.indexOf("7"));
        StringBuilder sbd = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sbd.append("这是第").append(i).append("个; ");
        }
        System.out.println(sbd);
        System.out.println(sbd.indexOf("7"));
    }

    /**
     * 测试值传递和地址传递
     * 结论。
     * 字符串：不管是new出来的对象，还是直接用=声明的都是值传递
     * 基本数据类型：同理，也是值传递。
     */
    @Test
    public void testValueAndAddressTransmit() {
        String transmitValue = "初始值";
        String transmitValueNew = new String("new 出来的字符串");
        Integer integer = new Integer(900);
        List<String> list = Lists.newArrayList();
        list.add("0");
        testTransmitValue(transmitValue, transmitValueNew, list, integer);
        System.out.println(transmitValue);
        System.out.println(transmitValueNew);
        System.out.println(list.toString());
        System.out.println(integer);
    }

    /**
     * 测试字符串和集合在函数之间的传值问题,解决值传递和地址传递的疑惑.
     */
    private static void testTransmitValue(String transmitValue, String transmitValueNew, List<String> list, Integer integer) {
        transmitValue += "修改的痕迹";
        transmitValueNew += "assss修改的痕迹";
        list.add("1");
        list.add("2");
        list.add("3");
        integer = 9999;
    }

    /**
     * 测试字符串常量池的问题
     */
    @Test
    public void testStringPool() {
        String a = "abc";//字面量形式
        String b = "abc";//字面量形式
        String c = new String("abc");//使用new标准的构造对象
        /*
            注意：这个虽然看起来似乎要在常量池新建三个字符串对象：ab，c，和拼接生成的abc
            但是结果是内存中仅有生成的，前面的两个算是过程变量。这反编译得出来的结论，我没测试哟！
            这样做实际上是一种优化，避免了创建多余的字符串对象，也没有发生字符串拼接问题
         */
        String d = "ab" + "c";//字面量形式
        System.out.println("a == b " + (a == b));//true
        System.out.println("a == c " + (a == c));//false
        System.out.println("a == d " + (a == d));//true
        System.out.println("b == c " + (b == c));//false
        System.out.println("b == d " + (b == d));//true
        System.out.println("c == d " + (c == d));//false
        System.out.println("-----------------");
        System.out.println("abc" == ("ab" + "c"));//true
        System.out.println("-----------------");
        String e = c.intern();//将new出来的字符串对象加入字符串常量池
        System.out.println(a == e);//true
        /*
            Java中字符串对象创建有两种形式。
            一种为字面量形式，如String str = "droid";，
            另一种就是使用new这种标准的构造对象的方法，如String str = new String("droid");
            这两种方式我们在代码编写时都经常使用，尤其是字面量的方式。然而这两种实现其实存在着一些性能和内存占用的差别。
            这一切都是源于JVM为了减少字符串对象的重复创建，其维护了一个特殊的内存，这段内存被成为字符串常量池或者字符串字面量池。
            工作原理
            当代码中出现字面量形式创建字符串对象时，JVM首先会对这个字面量进行检查。
            如果字符串常量池中存在相同内容的字符串对象的引用，则将这个引用返回。
            否则新的字符串对象被创建，然后将这个引用放入字符串常量池，并返回该引用。
         */
        System.out.println("-----------new test -----");
        String aa = "hello2";
        final String bb = "hello";
        String dd = "hello";
        String cc = bb + 2;
        String ee = dd + 2;
        String ff = "hello" + 2;
        System.out.println("------aa 和 cc ee ff 比较--------");
        System.out.println("aa == cc " + (aa == cc));//true,因为bb是final类型(这个会因为bb是否是final而结果不同，)
        System.out.println("aa == ee " + (aa == ee));//false
        System.out.println("aa == ff " + (aa == ff));//true,因为ff是直接由字面量形式创建出来的，不经过中间变量。
        //ff为字符串直接拼出来的，不经过中间变量。
        System.out.println("------ff 和 aa cc ee 比较--------");
        System.out.println("ff == aa " + (ff == aa));//true,因为ff是直接由字面量形式创建出来的，不经过中间变量。
        System.out.println("ff == cc " + (ff == cc));//true,因为bb是final类型(这个会因为bb是否是final而结果不同，)
        System.out.println("ff == ee " + (ff == ee));//false
    }
}
