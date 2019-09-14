package com.lxk.bean.model;


import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 使用final的Car类
 *
 * @author LiXuekai on 2019/6/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class CarFinal implements Cloneable, Comparable<Car>, Serializable {
    private String sign;
    private int price;
    private List<Dog> myDog;
    private List<String> boys;


    public CarFinal(String sign, int price) {
        this.sign = sign;
        this.price = price;
    }

    public CarFinal(String sign, int price, List<Dog> myDog) {
        this.sign = sign;
        this.price = price;
        this.myDog = myDog;
    }

    @Override
    public int compareTo(Car o) {
        //同理也可以根据sign属性排序，就不举例啦。
        return this.getPrice() - o.getPrice();
    }

    @Override
    public String toString() {
        return "Car{" +
                "sign='" + sign + '\'' +
                ", price=" + price +
                ", myDog=" + myDog +
                ", boys=" + boys +
                '}';
    }

    @Override
    public Car clone() {
        Car car = null;
        try {
            car = (Car) super.clone();
            if (myDog != null) {
                car.setMyDog(Lists.newArrayList(myDog));
            }
            if (boys != null) {
                car.setBoys(Lists.newArrayList(boys));
            }
        } catch (CloneNotSupportedException ignored) {
            System.out.println(ignored.getMessage());
        }
        return car;
    }

    public void run() {
        for (int i = 0; i < 1000; i++) {
            func();
        }
    }

    public final void finalRun() {
        for (int i = 0; i < 1000; i++) {
            func1();
        }
    }

    final void func1() {
        String s = this.toString();
    }

    void func() {
        String s = this.toString();
    }
}
