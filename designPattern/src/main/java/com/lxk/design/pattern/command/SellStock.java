package com.lxk.design.pattern.command;

/**
 * 卖股票
 *
 * @author LiXuekai on 2020/7/24
 */
public class SellStock implements Order {
    private Stock stock;

    public SellStock(Stock stock) {
        this.stock = stock;
    }

    public void execute() {
        stock.sell();
    }
}
