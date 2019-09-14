package com.lxk.jdk8.lambda;

import com.google.common.collect.Lists;
import com.lxk.bean.model.Person;

import java.util.Comparator;
import java.util.List;

/**
 * Java 8 lambda 来实现排序
 *
 * @author lxk on 2018/9/3
 */
public class OrderTest {
    public static void main(String[] args) {
        testLambdaSort();
    }

    /**
     * 直接这么干，是按系统默认的顺序去排序的。
     * 经过测试，发现这个jdk8的排序，还没得guava的呢个排序666，还是算了吧。
     * 1，不支持汉字按首字母排序，或者支持了也写的比较麻烦点
     * 2，对null或者empty的支持还是得自己来控制。
     */
    private static void testLambdaSort() {
        List<Person> persons = Lists.newArrayListWithExpectedSize(8);
        persons.add(new Person(11, "周星驰"));
        persons.add(new Person(44, "陈世美"));
        persons.add(new Person(22, "潘金莲"));
        persons.add(new Person(33, "阿姆斯特丹"));
        persons.add(new Person(44, "a"));
        persons.add(new Person(33, "b"));
        persons.add(new Person(22, "c"));
        persons.add(null);

        //persons.sort((final Person p1, final Person p2) -> {
        //    return p1.getName().compareTo(p2.getName());
        //});

        persons.sort(Comparator.comparing(Person::getAge));
        persons.forEach(person -> System.out.println(person.getAge()));

        //先按照一个排序，相同来，再按照第二个排序
        persons.sort(Comparator.comparing(Person::getAge).thenComparing(Person::getName));
    }













}
