package com.lxk.design.pattern.chainofresponsibility;

/**
 * @author LiXuekai on 2020/7/24
 */
public class ErrorLogger extends AbstractLogger {

    public ErrorLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }
}
