package com.lxk.jdk8.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 跳出for循环
 *
 * @author LiXuekai on 2018/10/23
 */
public class BreakForeach {
    public static void main(String[] args) {
        int max = 5;
        List<String> list = Arrays.asList("123", "12345", "1234", "4321", "1234567", "5678");
        continueForeachJava8(max, list);
        System.out.println();
        breakForeachJava8(max, list);
        System.out.println();
        continueFor(max, list);
        System.out.println();
        breakFor(max, list);
        System.out.println();
        breakManyFor(max, list);
    }

    /**
     * Java8跳过一次foreach循环，然后继续执行。
     */
    private static void continueForeachJava8(int max, List<String> list) {
        list.forEach(s -> {
            if (s.length() >= max) {
                return;
            }
            System.out.println(s);
        });
    }

    /**
     * 跳出Java8的foreach循环
     * 还这没找到，怎么跳出
     */
    private static void breakForeachJava8(int max, List<String> list) {
        //这么做是不对的
        //try {
        //    list.forEach(s -> {
        //        if (s.length() >= max) {
        //            throw new Exception();
        //        }
        //        System.out.println(s);
        //    });
        //} catch (Exception e) {
        //    System.out.println(e.getMessage());
        //}

        //看需求，建议使用如下之一。
        Optional<String> result = list.stream().filter(s -> s.length() >= max).findFirst();
        System.out.println(result.orElse(null));

        boolean lxk = list.stream().anyMatch(s -> s.length() >= max);
        System.out.println(lxk);
    }

    /**
     * continue 跳过本次循环，继续执行。
     */
    private static void continueFor(int max, List<String> list) {
        for (String s : list) {
            if (s.length() >= max) {
                continue;
            }
            System.out.println(s);
        }
    }

    /**
     * break 是直接跳出for循环，不再继续执行for循环到代码了。
     */
    private static void breakFor(int max, List<String> list) {
        for (String s : list) {
            if (s.length() >= max) {
                break;
            }
            System.out.println(s);
        }
    }

    /**
     * break lxk 是直接跳出多层for循环，不再继续执行for循环到代码了。
     */
    private static void breakManyFor(int max, List<String> list) {
        lxk:
        for (String s1 : list) {
            System.out.println("第一层：" + s1);
            for (String s2 : list) {
                System.out.println("第二层：" + s2);
                for (String s3 : list) {
                    if (s3.length() >= max) {
                        break lxk;
                    }
                    System.out.println("第三层：" + s3);
                }
            }
        }
    }

}
