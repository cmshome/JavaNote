package com.lxk.design.pattern.builder.meal.burger;

import com.lxk.design.pattern.builder.meal.pack.Wrapper;
import com.lxk.design.pattern.builder.meal.base.Item;
import com.lxk.design.pattern.builder.meal.pack.Packing;

/**
 * 创建实现 Item 接口的抽象类，该类提供了默认的功能
 *
 * @author LiXuekai on 2020/6/10
 */
public abstract class Burger implements Item {

    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}
