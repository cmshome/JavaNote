package com.lxk.jdk.common.string;

import com.google.common.collect.Lists;
import com.lxk.tool.PrintUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * 可变长度的参数列表
 *
 * @author LiXuekai on 2020/11/2
 */
public class ManyParameterTest {

    @Test
    public void test() {

        String a = "a";
        String ab = "ab";
        String abc = "abc";


        out();
        out(a);
        out(a, ab);
        out(a, ab, abc);

        String[] stringArray = {a, ab, abc};
        out(stringArray);

        out(Lists.newArrayList(a, ab, abc).toArray(new String[0]));
    }

    private void out(String... strings) {
        Arrays.stream(strings).forEach(System.out::println);
        PrintUtil.divideLine();
    }


}
