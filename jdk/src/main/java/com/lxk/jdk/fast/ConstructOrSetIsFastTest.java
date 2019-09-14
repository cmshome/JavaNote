package com.lxk.jdk.fast;

import com.google.common.collect.Lists;
import com.lxk.bean.model.Car;
import com.lxk.bean.model.Dog;
import org.junit.Test;

/**
 * 测试谁快 直接构造或者一个个set，他们的效率差多少
 *
 * @author LiXuekai on 2019/6/18
 */
public class ConstructOrSetIsFastTest {

    /**
     * 使用JProfiler看时间占比
     */
    @Test
    public void testFast() {
        while (true) {
            //27.4%
            set();
            //72.6%
            construct();
        }
    }

    /**
     * 使用JProfiler看时间占比
     */
    @Test
    public void testFast2() {
        while (true) {
            //33%
            set();
            //12.4%
            construct();
            //54.6%
            builder();
        }
    }

    /**
     * 使用lombok的 builder 模式来赋值
     */
    private static void builder() {
        Car car = Car.builder()
                .sign("0000")
                .price(100)
                .myDog(Lists.newArrayList(Dog.builder().name("aaa").alive(true).isLoyal(true).build()))
                .build();
    }


    /**
     * 构造函数来给属性赋值
     */
    private static void construct() {
        Car car = new Car("oooo", 100, Lists.newArrayList(new Dog("aaa", true, true)));
    }

    /**
     * set来给属性赋值
     */
    private static void set() {
        Car car = new Car();
        car.setSign("oooo");
        car.setPrice(100);
        Dog dog = new Dog();
        dog.setName("aaa");
        dog.setAlive(true);
        dog.setLoyal(true);
        car.setMyDog(Lists.newArrayList(dog));
    }
}
