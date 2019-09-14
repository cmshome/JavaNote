package com.lxk.questions;

/**
 * 计算1到n之间的数中出现1的次数，如1，，，10，11；这些数之中出现1的次数是4.
 *
 * @author lxk on 2017/7/7
 */
public class CountNumberOf1 {
    public static void main(String[] args) {
        Integer n = 111;
        count1(n);
    }

    /**
     * 计算1到n之间的数中出现1的次数
     */
    private static void count1(Integer n) {
        int result = 0;
        String tempNumber;
        char[] temp;
        String one = "1";
        char oneChar = '1';
        for (Integer i = 1; i < n + 1; i++) {
            tempNumber = i.toString();
            temp = tempNumber.toCharArray();
            if (tempNumber.contains(one)) {
                System.out.println(tempNumber);
                for (char aTemp : temp) {
                    if (oneChar == aTemp) {
                        result++;
                    }
                }
            }
        }
        System.out.println("1到" + n + "之间(包含边界值)的数字中1出现的次数是：" + result);
    }
}
