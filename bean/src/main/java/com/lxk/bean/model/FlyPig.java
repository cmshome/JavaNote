package com.lxk.bean.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lxk on 2017/11/1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlyPig implements Serializable {
    private static final long serialVersionUID = 1L;
    private static String AGE = "269";

    private String name;
    private String color;
    transient private String car;
    private String addTip;
    private Bird bird;


    @Override
    public String toString() {
        return "FlyPig{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", car='" + car + '\'' +
                ", AGE='" + AGE + '\'' +
                ", addTip='" + addTip + '\'' +
                '}';
    }
}
