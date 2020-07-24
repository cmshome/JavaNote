package com.lxk.design.pattern.state;

/**
 * @author LiXuekai on 2020/7/24
 */
public class Context {
    private State state;

    public Context(){
        state = null;
    }

    public void setState(State state){
        this.state = state;
    }

    public State getState(){
        return state;
    }
}
