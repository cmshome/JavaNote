package com.lxk.design.pattern.builder.meal.base;

import com.lxk.design.pattern.builder.meal.pack.Packing;

/**
 * 食物条目和食物包装的接口
 *
 * @author LiXuekai on 2020/6/10
 */
public interface Item {

    String name();

    Packing packing();

    float price();
}
