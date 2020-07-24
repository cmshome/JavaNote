package com.lxk.design.pattern.interpreter;

/**
 * @author LiXuekai on 2020/7/24
 */
public interface Expression {
    boolean interpret(String context);
}
