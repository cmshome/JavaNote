package com.lxk.jdk8.lambda.test;

import com.lxk.bean.model.Apple;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * 函数式接口
 *
 * @author LiXuekai on 2020/4/29
 */
public class Main {
    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(new Apple("green", 1L), new Apple("red", 2L), new Apple("red", 1L), new Apple("yellow", 3L));

        System.out.println(getAppleByColorOrWeight(list, apple -> apple.getColor().equals("red")));
        System.out.println(getAppleByColorOrWeight(list, apple -> apple.getWeight() > 1L));

        System.out.println(getAppleByColorAndWeight(list, (c, w) -> c.equals("red") && w > 1L));


        //java8还为我们提供了很多函数式接口，都在rt.jar里

        System.out.println(getAppleByCorlorOrWeight(list, apple -> apple.getColor().equals("red")));
        System.out.println(getAppleByCorlorOrWeight(list, apple -> apple.getWeight() > 1L));

        System.out.println(getAppleByCorlorAndWeight(list, (c, w) -> c.equals("red") && w > 1L));
    }

    /**
     * 一个参数返回Boolean
     */
    public static List<Apple> getAppleByColorOrWeight(List<Apple> apples, AppleTest<Apple> p) {
        List<Apple> result = new LinkedList<>();
        for (Apple apple : apples) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 两个参数返回Boolean
     */
    public static List<Apple> getAppleByColorAndWeight(List<Apple> apples, BiAppleTest<String, Long> p) {
        List<Apple> result = new LinkedList<>();
        for (Apple apple : apples) {
            if (p.test(apple.getColor(), apple.getWeight())) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 一个参数返回Boolean
     */
    public static List<Apple> getAppleByCorlorOrWeight(List<Apple> apples, Predicate<Apple> p) {
        List<Apple> result = new LinkedList<>();
        for (Apple apple : apples) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 两个参数返回Boolean
     */
    public static List<Apple> getAppleByCorlorAndWeight(List<Apple> apples, BiPredicate<String, Long> p) {
        List<Apple> result = new LinkedList<>();
        for (Apple apple : apples) {
            if (p.test(apple.getColor(), apple.getWeight())) {
                result.add(apple);
            }
        }
        return result;
    }


}
