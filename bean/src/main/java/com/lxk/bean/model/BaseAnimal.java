package com.lxk.bean.model;

import lombok.Data;

/**
 * @author lxk on 2018/5/25
 */
@Data
public abstract class BaseAnimal {

    private String name;

    /**
     * @param food 事物
     */
    void eat(String food) {
    }
}
