package com.lxk.design.pattern.prototype;

import lombok.Getter;
import lombok.Setter;

/**
 * @author LiXuekai on 2020/7/23
 */
public abstract class Shape implements Cloneable {

    @Getter @Setter
    private String id;
    @Getter
    protected String type;

    abstract void draw();


    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}
