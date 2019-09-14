package com.lxk.bean.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 测试model集合按某属性排序
 * <p>
 * @author lxk on 2016/11/25
 */
@Data
public class Person implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }


    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public Person clone() {
        Person person_ = null;
        try {
            person_ = (Person) super.clone();
        } catch (CloneNotSupportedException ignored) {
        }
        return person_;
    }
}