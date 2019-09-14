package com.lxk.jdk.common;

import com.google.common.collect.Lists;
import com.lxk.bean.model.Car;
import com.lxk.bean.model.Dog;
import com.lxk.bean.model.Student;

import java.util.Arrays;
import java.util.List;

/**
 * 数组测试
 *
 * @author lxk on  2017/2/10
 */
public class ArraysTest {
    public static void main(String[] args) {
        String order = "1128";
        if (!"1128".equals(order)) {
            testArrayInit();
            testJavaBeanArray();
            int[] arr = {1, 2, 3, 4, 5};
            changeValue(arr);
            System.out.println(Arrays.toString(arr));
        } else {
            testArraysCopy();
        }
    }

    /**
     * 数组当参数，是地址传递。
     */
    private static void changeValue(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] *= 2;
        }
    }


    /**
     * 测试对象数组的默认情况，默认全是null，基础知识啦。
     */
    private static void testJavaBeanArray() {
        Dog[] dogs = new Dog[10];
        for (Dog dog : dogs) {
            System.out.println(dog);
        }
    }

    /**
     * 二维数组初始化
     */
    private static void testArrayInit() {
        Object[][] ss = new Object[2][2];
        Object[][] ww = {{12, 12}, {12, 12}};
        //直接toString()，打印的是地址。
        System.out.println(Arrays.toString(ss));
        //直接toString()，打印的是地址。
        System.out.println(Arrays.deepToString(ss));
        System.out.println(Arrays.deepToString(ww));
    }

    /**
     * 数组复制：Arrays.copyOf()用法
     * 这个方法是浅拷贝，是地址传递。
     */
    private static void testArraysCopy() {
        Object[] numbers = {1, "ss", 3, 4, 5};
        Object[] numbersCopy = Arrays.copyOf(numbers, 5);
        numbersCopy[0] = 11;
        System.out.println("numbers      " + Arrays.toString(numbers));
        System.out.println("numbersCopy  " + Arrays.toString(numbersCopy));

        System.out.println();

        Student[] students = {getStudent(), getStudent(), getStudent()};
        System.out.println("修改之前的源数组：" + Arrays.toString(students));
        Student[] studentsCopy = Arrays.copyOf(students,3);
        studentsCopy[0].setName("这个是复制之后的修改第0个学生第名字。");
        System.out.println("修改之后的copy ：" + Arrays.toString(studentsCopy));
        System.out.println("修改之后的源数组：" + Arrays.toString(students));

    }

    /**
     * 获得测试对象
     */
    private static Student getStudent() {
        Dog dog1 = new Dog("大师兄的dog", true, true);
        Dog dog2 = new Dog("大师兄的dog", false, false);
        List<Dog> dogs = Lists.newArrayList();
        dogs.add(dog1);
        dogs.add(dog2);
        List<String> boys = Lists.newArrayList("tom", "jerry", "jack");
        Car car = new Car("q7", 182, dogs, boys);
        Student student = new Student();
        student.setName("Lxk");
        student.setCar(car);
        return student;
    }
}
