package com.lxk.jdk.constant;

/**
 * 常量：不是简单的直接全部丢在一起使用，而是对n多的常量进行分类分层管理，方便阅读和理解。
 *
 * @author LiXuekai on 2019/8/13
 */
public interface Constants {

    /**
     * 通用的常量
     */
    interface CommonConstant {
        String SPLIT = ",";
    }

    /**
     * 类别1
     */
    interface Metrics extends CommonConstant {
        String IP = "ip";
        String PORT = "port";
    }

    /**
     * 类别2
     */
    interface Numbers extends CommonConstant {
        int ZERO = 0;
        int ONE = 1;
        int TWO = 2;
        int THREE = 3;
        int FOUR = 4;
        int FIVE = 5;
    }
}
