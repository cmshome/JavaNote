package com.lxk.design.pattern.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiXuekai on 2020/7/24
 */
public class CareTaker {
    private List<Memento> mementoList = new ArrayList<>();

    public void add(Memento state) {
        mementoList.add(state);
    }

    public Memento get(int index) {
        return mementoList.get(index);
    }
}
