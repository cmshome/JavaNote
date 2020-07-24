package com.lxk.design.pattern.visitor;

/**
 * 计算机组成部分
 *
 * @author LiXuekai on 2020/7/24
 */
public interface ComputerPart {
    void accept(ComputerPartVisitor computerPartVisitor);
}
