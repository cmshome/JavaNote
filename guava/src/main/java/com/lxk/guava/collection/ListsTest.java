package com.lxk.guava.collection;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.lxk.bean.model.Car;
import com.lxk.bean.model.Dog;
import com.lxk.tool.collection.CollectionUtil;

import java.util.*;

/**
 * guava Lists 测试实例
 *
 * @author lxk on 2016/11/7
 */
public class ListsTest {

    public static void main(String[] args) {
        //emptyListToArray();
        //objectListToString();
        //simpleListToString();
        //testLists();
        //listCompare();
        //addIndexTest();
        //concurrentModificationException();
        //testStringList();
        List<Car> list = Lists.newArrayList();
        list.add(new Car("x", 11, Lists.newArrayList(new Dog())));
        list.add(new Car("xy", 12));
        list.add(new Car("xyz", 13));
        System.out.println(list.toString());
        modifyList(list);
        System.out.println(list.toString());
    }

    /**
     * 虽然list当参数是地址传递，但是，list里面的对象的字符串属性的修改是值传递，要改的话，还的set一下。
     */
    private static void modifyList(List<Car> list) {
        for (Car s : list) {
            if (s.getMyDog() != null) {
                Dog dog = s.getMyDog().get(0);
                dog.setName("wawdawda");
            }
        }
    }

    /**
     * 修改字符串list里面的值，是值传递。
     */
    private static void testStringList() {
        List<String> list = CollectionUtil.getArrayList(5);
        System.out.println(list.toString());
        for (String o : list) {
            o = "222";
        }
        System.out.println(list);

        for (int i = 0; i < list.size(); i++) {
            list.set(i,list.get(i)+ "aaaa");
        }
        System.out.println(list);
    }

    /**
     */
    private static void concurrentModificationException() {

    }

    /**
     * add(int index, E element);
     * 这个方法竟然不是替换掉index位置的元素，而是让index位置的元素向后靠，还在list里面。
     */
    private static void addIndexTest() {
        List<String> list = Lists.newArrayList();

        //bug ,,  IndexOutOfBoundsException
        //System.out.println(list.get(0));

        list.add(0, "0");
        list.add(0, "1");
        list.add(0, "2");
        list.add(0, "3");
        //输出：[3, 2, 1, 0]
        System.out.println(list.toString());

        List<String> list1 = new ArrayList<>();
        list1.add(0, "0");
        list1.add(0, "1");
        list1.add(0, "2");
        list1.add(0, "3");
        System.out.println(list1);

        LinkedHashMap<String, String> linkedHashMap = Maps.newLinkedHashMap();
        linkedHashMap.put("1", null);
        linkedHashMap.put("0", null);
        linkedHashMap.put("2", null);
        linkedHashMap.put("3", null);
        linkedHashMap.put("4", null);
        linkedHashMap.keySet().forEach(System.out::println);

        LinkedHashSet set = Sets.newLinkedHashSet();
    }

    /**
     * 把list转成set去比较交并补集
     */
    private static void listCompare() {
    }

    private static void simpleListToString() {
        List<String> list = Lists.newArrayList();
        System.out.println(list.toString());
        //跳过null
        Joiner joiner = Joiner.on(",").skipNulls();
        String s = joiner.join(list);
        System.out.println(s);
        List<String> a = Lists.newArrayList();
        List<String> b = Lists.newArrayList();
        b.addAll(a);

        List<String> ss = Lists.newArrayList("1", "2", "3", "4", "5");
        System.out.println(ss.toString());
        ss = ss.subList(1,5);
        System.out.println(ss.toString());
        ss.add("2333");
        System.out.println(ss.toString());

    }


    /**
     * list.toString()
     */
    private static void objectListToString() {
        List<String> s = Lists.newArrayList();
        List<String> ss = Lists.newArrayList();
        List<Car> list1 = Lists.newArrayList();
        List<Car> list2 = Lists.newArrayList();
        Dog dog = new Dog(true, true);
        List<Dog> dogs = Lists.newArrayList();
        dogs.add(dog);
        for (int i = 0; i < 5; i++) {
            s.add(i + "saa");
            ss.add(i + "saa");
            list1.add(new Car("i", 100 + i, dogs));
            list2.add(new Car("i", 100 + i, dogs));
        }
        //List<普通类型>，直接toString()，可以输出内容。
        System.out.println(s.toString().equals(ss.toString()));
        System.out.println(s.toString());
        //List<对象类型>，直接toString()，若对应model没复写toString()方法，输出是地址；复写toString()之后，输出就不是地址。
        System.out.println(list1.toString());
        System.out.println(list2.toString());
        System.out.println(list1.toString().equals(list2.toString()));
    }

    private static void emptyListToArray() {
        List<String> list = Lists.newArrayList();
        String[] array = list.toArray(new String[list.size()]);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 测试 guava Lists
     */
    private static void testLists() {

        List<String> list1 = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            list1.add(i + "");
        }
        System.out.println("list1: " + list1);
        List<String> list2 = Lists.newArrayList("1", "2", "3");
        System.out.println("list2: " + list2);
        List<String> list3 = Lists.newArrayList(new String[]{"22", "22"});
        System.out.println("list3: " + list3);
        List<String> list4 = Lists.newArrayList(list1);
        System.out.println("list4: " + list4);

        //下面的这个写法呢是在初始化list的时候，说明容器的扩容界限值

        //使用条件：你确定你的容器会装多少个，不确定就用一般形式的

        //说明：这个容器超过10个还是会自动扩容的。不用担心容量不够用。默认是分配一个容量为10的数组，不够将扩容
        //执行数组数据迁移操作：新建新数组，复制就数组数据到新数组（包括开辟新空间，copy数据等等，耗时，耗性能）

        //对下数字10的说明：若直接new一个list的话，默认大小是10的数组，下面的方式则是 5L + x + x/10 = 16L(x = 10)，在17的时候扩容

        //整个来说的优点有：节约内存，节约时间，节约性能。代码质量提高。
        List<String> list = Lists.newArrayListWithExpectedSize(10);

        //这个方法就是直接返回一个10的数组。
        List<String> list_ = Lists.newArrayListWithCapacity(10);
    }
}
