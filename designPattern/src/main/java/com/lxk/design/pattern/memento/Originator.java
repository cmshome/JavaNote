package com.lxk.design.pattern.memento;

/**
 * Originator
 * n. 创始人；发明者； 提出者
 *
 * @author LiXuekai on 2020/7/24
 */
public class Originator {
    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public Memento saveStateToMemento() {
        return new Memento(state);
    }

    public void getStateFromMemento(Memento Memento) {
        state = Memento.getState();
    }
}
