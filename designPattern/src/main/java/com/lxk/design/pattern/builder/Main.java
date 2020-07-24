package com.lxk.design.pattern.builder;

import com.lxk.design.pattern.builder.meal.Meal;
import com.lxk.design.pattern.builder.meal.MealBuilder;

/**
 * 建造者模式（Builder Pattern）
 * 使用多个简单的对象一步一步构建成一个复杂的对象。这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。
 * 优点：
 *      1、建造者独立，易扩展。
 *      2、便于控制细节风险。
 * 缺点：
 *      1、产品必须有共同点，范围有限制。
 *      2、如内部变化复杂，会有很多的建造类。
 *
 * @author LiXuekai on 2020/6/10
 */
public class Main {

    public static void main(String[] args) {
        kfc();
    }

    private static void kfc() {
        MealBuilder mealBuilder = new MealBuilder();

        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("Veg Meal");
        vegMeal.showItems();
        System.out.println("Total Cost: " + vegMeal.getCost());

        Meal nonVegMeal = mealBuilder.prepareNonVegMeal();
        System.out.println("Non-Veg Meal");
        nonVegMeal.showItems();
        System.out.println("Total Cost: " + nonVegMeal.getCost());
    }
}
