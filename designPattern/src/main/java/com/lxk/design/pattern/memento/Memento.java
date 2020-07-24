package com.lxk.design.pattern.memento;

/**
 * Memento 纪念品
 * n. 记忆碎片（电影名）
 *
 * @author LiXuekai on 2020/7/24
 */
public class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
