package com.lxk.design.pattern.interceptingfilter;

/**
 * @author LiXuekai on 2020/7/26
 */
public class DebugFilter implements Filter {
    public void execute(String request) {
        System.out.println("request log: " + request);
    }
}
