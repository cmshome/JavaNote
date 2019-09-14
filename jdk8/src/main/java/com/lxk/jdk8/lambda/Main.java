package com.lxk.jdk8.lambda;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lxk.bean.model.User;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Java 8 之 lambda测试
 * 中间操作：filter()、distinct()、sorted()、map()、flatMap()等，其一般是对数据集的整理（过滤、排序、匹配、抽取等）。
 * 终结操作：如forEach()、allMatch()、anyMatch()、findAny()、 findFirst()，
 * ----------数值计算类的方法有sum、max、min、average等等。
 * ----------终止方法也可以是对集合的处理，如reduce()、 collect()等等
 * <p>
 * <p>
 * @author lxk on 2017/8/28
 */
public class Main {
    public static void main(String[] args) {
        //testLoop();
        //testLoopOperate();
        //testStreamFilter();
        testStreamMap();
        //testOperateNumber();
        //testReduce();
        //testCollect();
    }

    /**
     * collect的基础方法
     */
    private static void testCollect() {
        Stream stream = Stream.of(1, 2, 3, 4).filter(p -> p > 2);
        List<Integer> collect = (List<Integer>) stream.collect(Collectors.toList());
        collect.forEach(s -> System.out.print(s + " "));

        List<User> users = getLoopList();
        List<Map> resultList = users.stream().collect(Lists::newArrayList, (list, user) -> {
            Map<String, String> userMap = Maps.newHashMap();
            userMap.put(user.getName(), user.getPwd());
            list.add(userMap);
        }, List::addAll);
        resultList.forEach(System.out::println);

        Map<Integer, Integer> map = getLoopMap();
        map.forEach((k, v) -> System.out.println("key " + k + " v " + v));
    }

    /**
     * T reduce(T identity, BinaryOperator<T> accumulator);
     * identity：它允许用户提供一个循环计算的初始值。
     * accumulator：计算的累加器，
     */
    private static void testReduce() {
        //T reduce(T identity, BinaryOperator<T> accumulator);
        System.out.println("给定个初始值，求和");
        System.out.println(Stream.of(1, 2, 3, 4).reduce(100, (sum, item) -> sum + item));
        System.out.println(Stream.of(1, 2, 3, 4).reduce(100, Integer::sum));
        System.out.println("给定个初始值，求min");
        System.out.println(Stream.of(1, 2, 3, 4).reduce(100, (min, item) -> Math.min(min, item)));
        System.out.println(Stream.of(1, 2, 3, 4).reduce(100, Integer::min));
        System.out.println("给定个初始值，求max");
        System.out.println(Stream.of(1, 2, 3, 4).reduce(100, (max, item) -> Math.max(max, item)));
        System.out.println(Stream.of(1, 2, 3, 4).reduce(100, Integer::max));

        //Optional<T> reduce(BinaryOperator<T> accumulator);
        // 注意返回值，上面的返回是T,泛型，传进去啥类型，返回就是啥类型。
        // 下面的返回的则是Optional类型
        System.out.println("无初始值，求和");
        System.out.println(Stream.of(1, 2, 3, 4).reduce(Integer::sum).orElse(0));
        System.out.println("无初始值，求max");
        System.out.println(Stream.of(1, 2, 3, 4).reduce(Integer::max).orElse(0));
        System.out.println("无初始值，求min");
        System.out.println(Stream.of(1, 2, 3, 4).reduce(Integer::min).orElse(0));

        System.out.println(Stream.of(1, 2, 3, 4).max(Integer::max));
        System.out.println(Stream.of(1, 2, 3, 4).max(Comparator.naturalOrder()));

    }

    /**
     * 测试用法，稍复杂的串行操作。
     * 过滤空值；去重；排序；循环输出。
     */
    private static void testOperateNumber() {
        List<Integer> integers = Arrays.asList(1, 3, null, 8, 7, 8, 13, 10);
        integers.stream().filter(Objects::nonNull).distinct().sorted().forEach(System.out::println);
    }

    /**
     * 测试collection.stream().filter()
     */
    private static void testStreamFilter() {
        List<User> list = getLoopList();
        System.out.println("对集合进行过滤：user name 包含 1 的用户");
        List<User> filtered = list.stream().filter(s -> s.getName().contains("1")).collect(Collectors.toList());
        filtered.forEach(user -> System.out.println(user.toString()));
        println();
    }

    /**
     * 测试collection.stream().map()
     */
    private static void testStreamMap() {
        List<User> list = getLoopList();
        System.out.println("对集合进行操作：user name + “1” ");
        List<String> nameList = list.stream().map(User::getName).collect(Collectors.toList());
        nameList.forEach(System.out::print);
        println();
        List<Integer> all = Lists.newArrayList(100, 200, 300, 400, 500);
        all.stream().map((cost) -> cost + .12 * cost).forEach(System.out::println);
    }

    /**
     * 测试循环的时候，顺带操作集合中的内容。
     */
    private static void testLoopOperate() {
        List<User> list = getLoopList();
        lambdaOperateList(list);

        Map<Integer, Integer> map = getLoopMap();
        lambdaOperateMap(map);
    }

    private static void lambdaOperateList(List<User> list) {
        System.out.println("对list集合进行操作：user's name + “_1” ");
        //list.forEach(user -> user.setName(user.getName() + "_1"));
        list.forEach(user -> {
            String name = user.getName();
            user.setName(name + "_1");

            //在循环的时候，不能修改list结构(包括新增和删除).
            //不然会出现此异常：Exception in thread "main" java.util.ConcurrentModificationException
            //if(user.getPwd().contains("1")){
            //    list.remove(user);
            //}
            //if(user.getPwd().contains("1")){a
            //    list.add(new User("10","10"));
            //}
        });
        //操作完，循环输出一下，看下是否操作OK。
        list.forEach(System.out::println);
        println();
    }

    private static void lambdaOperateMap(Map<Integer, Integer> map) {
        System.out.println("对map集合进行操作：map's value + 10 ");
        map.forEach((k, v) -> {
            v = v + 10;
            map.put(k, v);//更新。

            //在循环的时候，不能修改map结构(包括新增和删除).
            //不然会出现此异常：Exception in thread "main" java.util.ConcurrentModificationException
            //if(k == 1){
            //    map.remove(k);
            //}
            //if(k == 9){
            //    map.put(10,10);//添加，
            //}
        });
        lambdaLoopMap(map);
        println();
    }

    /**
     * 测试集合(list,map)循环
     */
    private static void testLoop() {
        List<User> list = getLoopList();
        beforeLoopList(list);
        lambdaLoopList(list);

        Map<Integer, Integer> map = getLoopMap();
        beforeLoopMap(map);
        lambdaLoopMap(map);
    }

    /**
     * 以前循环一个集合，for和foreach循环
     */
    private static void beforeLoopList(List<User> list) {
        for (User user : list) {
            System.out.println(user.toString());
        }
        println();
    }

    /**
     * 使用lambda循环一个集合
     */
    private static void lambdaLoopList(List<User> list) {
        //三行循环效果相同
        //list.forEach(user -> System.out.println(user.toString()));
        //list.forEach(user -> System.out.println(user));//下面一行代码就是简写形式
        list.forEach(System.out::println);
        println();
    }

    /**
     * Java 7 的map的遍历
     */
    private static void beforeLoopMap(Map<Integer, Integer> map) {
        System.out.println("Java 7 遍历map");
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println("k " + entry.getKey() + " v " + entry.getValue());
        }
        for (Integer key : map.keySet()) {
            System.out.println("k " + key);
        }
        for (Integer value : map.values()) {
            System.out.println("v " + value);
        }
        println();
    }

    /**
     * Java 8 的map的遍历
     */
    private static void lambdaLoopMap(Map<Integer, Integer> map) {
        System.out.println("Java 8 遍历map");
        map.forEach((k, v) -> System.out.println("k " + k + " v " + v));
    }

    /**
     * 获得一个list集合
     */
    private static List<User> getLoopList() {
        List<User> all = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            all.add(new User("lxk_" + i, "pwd_" + i));
        }
        return all;
    }

    /**
     * 获得一个map集合
     */
    private static Map<Integer, Integer> getLoopMap() {
        Map<Integer, Integer> map = Maps.newHashMap();
        for (int i = 0; i < 10; i++) {
            map.put(i, i);
        }
        return map;
    }

    /**
     * 打印分行符公共方法
     */
    private static void println() {
        System.out.println("___________________我是分行符___________________");
    }

}
