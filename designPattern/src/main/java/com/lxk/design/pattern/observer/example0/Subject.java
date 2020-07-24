package com.lxk.design.pattern.observer.example0;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiXuekai on 2020/7/24
 */
public class Subject {

    private List<Observer> observers = new ArrayList<>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    /**
     * attach
     * vi. 附加；附属；伴随
     * vt. 使依附；贴上；系上；使依恋
     */
    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
