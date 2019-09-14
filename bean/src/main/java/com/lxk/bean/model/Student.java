package com.lxk.bean.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lxk on 2017/3/23
 */
@Data
public class Student implements Cloneable, Serializable {
    private String name;
    private Car car;


    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", car=" + car +
                '}';
    }

    @Override
    public Student clone() {
        Student student = null;
        try {
            student = (Student) super.clone();
            if (car != null) {
                student.setCar(car.clone());
            }
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        return student;
    }
}
