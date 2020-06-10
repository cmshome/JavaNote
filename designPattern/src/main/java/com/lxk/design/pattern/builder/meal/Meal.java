package com.lxk.design.pattern.builder.meal;

import com.lxk.design.pattern.builder.meal.base.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * 一餐，一顿饭；膳食
 *
 * @author LiXuekai on 2020/6/10
 */
public class Meal {
    private List<Item> items = new ArrayList<Item>();

    public void addItem(Item item) {
        items.add(item);
    }

    public float getCost() {
        float cost = 0.0f;
        for (Item item : items) {
            cost += item.price();
        }
        return cost;
    }

    public void showItems() {
        for (Item item : items) {
            System.out.print("Item : " + item.name());
            System.out.print(", Packing : " + item.packing().pack());
            System.out.println(", Price : " + item.price());
        }
    }
}
