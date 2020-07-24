package com.lxk.design.pattern.state;

/**
 * @author LiXuekai on 2020/7/24
 */
public class StopState implements State {

    public void doAction(Context context) {
        System.out.println("Player is in stop state");
        context.setState(this);
    }

    public String toString(){
        return "Stop State";
    }
}
