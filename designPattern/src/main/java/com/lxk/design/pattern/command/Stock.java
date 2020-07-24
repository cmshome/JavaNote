package com.lxk.design.pattern.command;

/**
 * 股票
 *
 * @author LiXuekai on 2020/7/24
 */
public class Stock {

    private String name = "aLiBaBa";
    /**
     * quantity
     * n. 量，数量；大量；总量
     */
    private int quantity = 10;

    public void buy() {
        System.out.println("Stock [ Name: " + name + ", Quantity: " + quantity + " ] bought");
    }

    public void sell() {
        System.out.println("Stock [ Name: " + name + ", Quantity: " + quantity + " ] sold");
    }
}
