package com.lxk.questions;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * 看似简单，实则暗藏玄机的几个程序题。作对的寥寥无几。
 *
 * @author lxk on 2017/2/23
 */
public class Questions {
    /**
     * questions1
     */
    private static void questions1() {
        String str = "str";
        Questions.appendStr(str);
        System.out.println(str);
    }

    private static void appendStr(String str) {
        str += "1";
    }

    /**
     * inner class A B
     */
    static class A {

        static {
            System.out.print("0");
        }

        private static String staticStr = initStaticStr();

        {
            System.out.print("1");
        }

        private String str = initStr();

        public A() {
            System.out.print("2");
        }

        private static String initStaticStr() {
            System.out.print("4");
            return "initStaticStr";
        }

        private String initStr() {
            System.out.print("3");
            return "initStr";
        }
    }

    static class B extends A {
        public B() {
            System.out.print("5");
        }
    }

    /**
     * questions2
     */
    private static void questions2() {

        new B();
        System.out.println();
    }

    /**
     * questions3
     */
    private static void questions3() {
        int a = 3, b = 4;
        if (a++ == 3 && ++b == 5 && ++a + b++ == 10 && a++ == 7 && ++b == 11) {
            System.out.println("a:" + a + "   b:" + b);
        } else {
            System.out.println("a:" + a + "   b:" + b);
        }
    }

    /**
     * questions4
     */
    private static void questions4() {
        int i = 0;
        try {
            for (; i < 5; i++) {
                if (i == 3) {
                    throw new Exception("i==3");
                }
            }
            i -= 10;
        } catch (Exception ignored) {
        } finally {
            i += 10;
        }
        System.out.println(i);
    }

    /**
     * questions5
     */
    public static void questions5() {
        int result = 0;
        int i = 2;
        switch (i) {
            case 1:
                result = result + i;
            case 2:
                result = result + i * 2;
            case 3:
                result = result + i * 3;
                break;
        }
        System.out.println(result);
    }

    /**
     * 计算集合的交集、并集、差集
     */
    private static void questions6() {
        Integer[] a = {1, 3, 5, 7};
        Integer[] b = {1, 2, 5};
        List<Integer> list1 = Arrays.asList(a);
        List<Integer> list2 = Arrays.asList(b);
        Set<Integer> all = Sets.newHashSet();
        List<Integer> share = Lists.newArrayList();
        all.addAll(list1);
        for (Integer integer : list2) {
            if (!all.add(integer)) {
                share.add(integer);
            }
        }
        System.out.println("并集" + all);
        System.out.println("交集" + share);
        Set<Integer> special = Sets.difference(Sets.newHashSet(all), Sets.newHashSet(share));
        System.out.println("差集" + special);
        Integer[] s = special.toArray(new Integer[special.size()]);
        System.out.println("数组" + Arrays.toString(s));
    }

    /**
     * 用递归算法写一段程序实现 1+2+...+1000000
     */
    public static long questions7(long sum) {
        if (sum == 0) {
            return sum;
        }
        return sum + questions7(sum - 1);
    }

    public static void main(String[] args) {
        questions1();
        questions2();
        questions3();
        questions4();
        questions5();
        questions6();
        long result = questions7(100);
        System.out.println(result);
    }
}

