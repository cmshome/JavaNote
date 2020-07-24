package com.lxk.design.pattern.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Broker
 * n. 经纪人，掮客
 * vt. 以中间人等身分安排...
 * vi. 作为权力经纪人进行谈判
 *
 * @author LiXuekai on 2020/7/24
 */
public class Broker {
    private List<Order> orderList = new ArrayList<>();

    /**
     * 点酒水、点菜；服从指挥
     */
    public void takeOrder(Order order) {
        orderList.add(order);
    }

    /**
     * 订购 下订单
     */
    public void placeOrders() {
        for (Order order : orderList) {
            order.execute();
        }
        orderList.clear();
    }
}
