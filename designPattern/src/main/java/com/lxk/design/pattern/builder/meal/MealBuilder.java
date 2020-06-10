package com.lxk.design.pattern.builder.meal;

import com.lxk.design.pattern.builder.meal.burger.ChickenBurger;
import com.lxk.design.pattern.builder.meal.burger.VegBurger;
import com.lxk.design.pattern.builder.meal.drink.Coke;
import com.lxk.design.pattern.builder.meal.drink.Pepsi;

/**
 * @author LiXuekai on 2020/6/10
 */
public class MealBuilder {

    public Meal prepareVegMeal (){
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    public Meal prepareNonVegMeal (){
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}
