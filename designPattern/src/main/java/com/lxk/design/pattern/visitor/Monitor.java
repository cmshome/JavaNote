package com.lxk.design.pattern.visitor;

/**
 * 显示器
 *
 * @author LiXuekai on 2020/7/24
 */
public class Monitor implements ComputerPart {

    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}
