package com.lxk.design.pattern.chainofresponsibility;

/**
 * @author LiXuekai on 2020/7/24
 */
public class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger: " + message);
    }
}
