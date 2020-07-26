package com.lxk.design.pattern.interceptingfilter;

/**
 * @author LiXuekai on 2020/7/26
 */
public class AuthenticationFilter implements Filter {
    public void execute(String request) {
        System.out.println("Authenticating request: " + request);
    }
}
