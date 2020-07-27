package com.lxk.jdk.common.string;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author LiXuekai on 2020/7/27
 */
public class SplitTest {

    /**
     * 1，| . 要转义
     * 2，若是有split之后数组的期望的值，则使用 split(String regex, int limit)
     */
    @Test
    public void split() {
        String s = "0|probeName_lxk|188.188.8.118|xxx";
        String[] split = s.split("\\|");
        // [0, probeName_lxk, 188.188.8.118, xxx]
        System.out.println(Arrays.toString(split));

        s = "0|probeName_lxk|188.188.8.118|";
        split = s.split("\\|");
        // [0, probeName_lxk, 188.188.8.118]
        System.out.println(Arrays.toString(split));

        split = s.split("\\|", 4);
        // [0, probeName_lxk, 188.188.8.118, ]
        System.out.println(Arrays.toString(split));

        s = "trans_ref.amount";
        split = s.split("\\.");
        // [trans_ref, amount]
        System.out.println(Arrays.toString(split));

    }
}
