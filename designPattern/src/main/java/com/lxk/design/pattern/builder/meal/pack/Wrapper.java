package com.lxk.design.pattern.builder.meal.pack;

/**
 * 包装器-打包
 *
 * @author LiXuekai on 2020/6/10
 */
public class Wrapper implements Packing {

    @Override
    public String pack() {
        return "Wrapper-包装器";
    }
}
