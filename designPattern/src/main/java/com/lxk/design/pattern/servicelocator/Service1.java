package com.lxk.design.pattern.servicelocator;

/**
 * @author LiXuekai on 2020/7/26
 */
public class Service1 implements Service {
    public void execute() {
        System.out.println("Executing Service1");
    }

    @Override
    public String getName() {
        return "Service1";
    }
}
