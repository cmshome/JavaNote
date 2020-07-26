package com.lxk.design.pattern.interceptingfilter;

/**
 * @author LiXuekai on 2020/7/26
 */
public class Target {
    public void execute(String request) {
        System.out.println("Executing request: " + request);
    }
}
