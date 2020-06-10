package com.lxk.design.pattern.builder.meal.pack;

/**
 * 把…装入瓶中
 *
 * @author LiXuekai on 2020/6/10
 */
public class Bottle implements Packing {

    @Override
    public String pack() {
        return "Bottle - 把…装入瓶中";
    }
}

