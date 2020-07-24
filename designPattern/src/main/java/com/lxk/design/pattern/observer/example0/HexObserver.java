package com.lxk.design.pattern.observer.example0;

/**
 * @author LiXuekai on 2020/7/24
 */
public class HexObserver extends Observer{

    public HexObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Hex String: " + Integer.toHexString( subject.getState() ).toUpperCase() );
    }
}
