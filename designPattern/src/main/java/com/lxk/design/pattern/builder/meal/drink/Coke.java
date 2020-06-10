package com.lxk.design.pattern.builder.meal.drink;

/**
 * 可口可乐
 *
 * @author LiXuekai on 2020/6/10
 */
public class Coke extends ColdDrink {

    @Override
    public float price() {
        return 30.0f;
    }

    @Override
    public String name() {
        return "Coke-可口可乐";
    }
}
