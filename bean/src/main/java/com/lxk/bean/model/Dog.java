package com.lxk.bean.model;

import com.google.common.base.Objects;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 测试boolean属性的getter和setter
 * <p>
 * @author lxk on 2016/12/23
 */
@Builder(toBuilder = true)
@Data
public class Dog implements Serializable {
    private String name;
    /**
     * 忠诚的
     */
    private boolean isLoyal;
    /**
     * 活蹦乱跳的
     */
    private boolean alive;


    public Dog() {
    }

    public Dog(boolean isLoyal, boolean alive) {
        this.isLoyal = isLoyal;
        this.alive = alive;
    }

    public Dog(String name, boolean isLoyal, boolean alive) {
        this.name = name;
        this.isLoyal = isLoyal;
        this.alive = alive;
    }

    /**
     * @param detail 狗叫的内容
     */
    public String bark(String detail) {
        System.out.println("barking " + detail);
        return detail;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Dog)) {
            return false;
        }
        Dog dog = (Dog) o;
        return Objects.equal(getName(), dog.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", isLoyal=" + isLoyal +
                ", alive=" + alive +
                '}';
    }
}
