package com.lxk.design.pattern.builder.meal.drink;

import com.lxk.design.pattern.builder.meal.base.Item;
import com.lxk.design.pattern.builder.meal.pack.Bottle;
import com.lxk.design.pattern.builder.meal.pack.Packing;

/**
 * 冷饮
 *
 * @author LiXuekai on 2020/6/10
 */
public abstract class ColdDrink implements Item {

    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();
}
