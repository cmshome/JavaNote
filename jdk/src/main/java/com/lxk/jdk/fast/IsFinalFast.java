package com.lxk.jdk.fast;

import com.google.common.collect.Lists;
import com.lxk.bean.model.Car;
import com.lxk.bean.model.CarFinal;
import com.lxk.bean.model.Dog;

/**
 * 有人说给方法或者类加上final之后，效率会提升，我来试试。
 *
 * @author LiXuekai on 2019/6/20
 */
public class IsFinalFast {
    public static void main(String[] args) {
        //methodTest();
        classTest();
    }

    private static void classTest() {
        while (true) {
            classFinal();
            classNoFinal();
        }
    }

    private static void classFinal() {
        CarFinal car = new CarFinal("oooo", 100, Lists.newArrayList(new Dog("aaa", true, true)));
        car.finalRun();
    }

    private static void classNoFinal() {
        Car car = new Car("oooo", 100, Lists.newArrayList(new Dog("aaa", true, true)));
        car.run();
    }

    private static void methodTest() {
        Car car = new Car("oooo", 100, Lists.newArrayList(new Dog("aaa", true, true)));
        while (true) {
            noFinal(car);
            isFinal(car);
        }
    }

    private static void isFinal(Car car) {
        car.finalRun();
    }

    private static void noFinal(Car car) {
        car.run();
    }
}
