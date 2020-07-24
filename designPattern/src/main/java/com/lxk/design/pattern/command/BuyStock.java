package com.lxk.design.pattern.command;

/**
 * 买股票
 *
 * @author LiXuekai on 2020/7/24
 */
public class BuyStock implements Order {
    private Stock stock;

    public BuyStock(Stock stock) {
        this.stock = stock;
    }

    public void execute() {
        stock.buy();
    }
}
