package com.lxk.design.pattern.builder.meal.drink;

/**
 * 百事可乐
 *
 * @author LiXuekai on 2020/6/10
 */
public class Pepsi extends ColdDrink {

    @Override
    public float price() {
        return 35.0f;
    }

    @Override
    public String name() {
        return "Pepsi-百事可乐";
    }
}
