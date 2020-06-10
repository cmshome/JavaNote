package com.lxk.design.pattern.builder.meal.burger;

/**
 * @author LiXuekai on 2020/6/10
 */
public class VegBurger extends Burger {

    @Override
    public float price() {
        return 25.0f;
    }

    @Override
    public String name() {
        return "Veg Burger";
    }
}