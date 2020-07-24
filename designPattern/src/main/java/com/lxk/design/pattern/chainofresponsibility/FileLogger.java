package com.lxk.design.pattern.chainofresponsibility;

/**
 * @author LiXuekai on 2020/7/24
 */
public class FileLogger extends AbstractLogger {

    public FileLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}
