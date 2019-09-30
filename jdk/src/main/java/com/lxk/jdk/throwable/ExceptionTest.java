package com.lxk.jdk.throwable;

import com.google.common.collect.Lists;
import com.lxk.bean.model.Child;
import com.lxk.bean.model.Parent;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 常见的异常（运行时异常:runtimeException，一般异常。）
 *
 * @author lxk on 2018/3/21
 */
public class ExceptionTest {
    public static void main(String[] args) {
        //runtimeException();
        checkedException();
    }

    /**
     * Checked异常都是可以被处理的异常
     * ClassNotFoundException
     * NoSuchMethodException
     * IOException
     */
    private static void checkedException() {
    }

    /**
     * 常见的运行时异常：
     * 1，空指针异常：      NullPointerException
     * 2，类转换异常：      ClassCastException
     * 3，下标越界异常：    IndexOutOfBoundsException
     * 4，并发操作异常：    ConcurrentModificationException
     * 5，参数不合法：      IllegalArgumentException
     * 6，不支持的操作：    UnsupportedOperationException
     * 7，除数为0：         ArithmeticException
     */
    private static void runtimeException() {
        nullPointerException();
        classCastException();
        indexOutOfBoundsException();
        concurrentModificationExceptionTest();
        illegalArgumentException();
        unsupportedOperationException();
        arithmeticException();
    }

    /**
     * ArithmeticException
     */
    private static void arithmeticException() {
        System.out.println(100 / 0);
    }

    private static void unsupportedOperationException() {
        String[] arr = new String[] {"str1", "str2"};
        List<String> listSpecial = Arrays.asList(arr);
        listSpecial.add("ss");
    }

    /**
     * 参数不合法，list集合初始化容量不能小于0。
     */
    private static void illegalArgumentException() {
        List<String> list = Lists.newArrayListWithExpectedSize(-9);
    }

    /**
     * 多线程操作时的异常
     */
    private static void concurrentModificationExceptionTest() {
        modCountTest();
    }

    /**
     * 下标越界异常
     */
    private static void indexOutOfBoundsException() {
        List<String> list = Lists.newArrayList();
        list.add(3,"lxk");
    }

    /**
     * 类转换异常
     */
    private static void classCastException() {
        Parent parent = new Parent();
        Child child = (Child) parent;
    }

    /**
     * 空指针异常
     */
    private static void nullPointerException() {
        String a = null;
        a.equals("ss");
    }

    /**
     * modCount 相关测试
     */
    private static void modCountTest() {
        List<String> list = Lists.newArrayList();
        //给list设值:{0-9}
        setListData(list);
        System.out.println("原来未修改集合" + list);
        //1：使用迭代器迭代的时候，集合结构遭到修改
        wrongWay0(list);
        //for循环删除安全
        rightWay1(list);
        //2：for each 删除集合元素
        wrongWay1(list);
        //3：多线程并发操作一个集合时候
        wrongWay2(list);
        System.out.println("被修改过的集合" + list);
    }

    /**
     * 获得一个list集合
     */
    private static void setListData(List<String> list) {
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }
    }

    /**
     * 第一种：会抛 ConcurrentModificationException 的异常方法
     */
    private static void wrongWay0(List<String> list) {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String temp = iterator.next();
            if (iterator.next().equals("5")) {
                //正确移除集合元素姿势
                //iterator.remove();
                //抛异常移除集合元素姿势
                list.remove(temp);
            }
        }
    }

    /**
     * 第二种：会抛ConcurrentModificationException的异常方法
     */
    private static void wrongWay1(List<String> list) {
        for (String s : list) {
            if ("5".equals(s)) {
                list.remove(s);
            }
        }
    }

    /**
     * 第二种：不抛异常姿势。
     * （若只是简单循环集合，可用for each循环，若要增删集合，则用for i 循环,避免出错）
     */
    private static void rightWay1(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if ("5".equals(list.get(i))) {
                list.remove(i);
            }
        }
    }

    /**
     * 第三种：会抛ConcurrentModificationException的异常方法
     */
    private static void wrongWay2(final List<String> list) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Iterator<String> iterator = list.iterator();
                while (iterator.hasNext()) {
                    System.out.println(iterator.next());
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).equals("4")) {
                        list.remove(i);
                    }
                }
            }
        }).start();
    }
}
