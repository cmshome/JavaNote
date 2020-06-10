package com.lxk.design.pattern.builder.meal.burger;

/**
 * @author LiXuekai on 2020/6/10
 */
public class ChickenBurger extends Burger {

    @Override
    public float price() {
        return 50.5f;
    }

    @Override
    public String name() {
        return "Chicken Burger";
    }
}
