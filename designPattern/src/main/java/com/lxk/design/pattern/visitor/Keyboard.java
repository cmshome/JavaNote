package com.lxk.design.pattern.visitor;

/**
 * 键盘
 *
 * @author LiXuekai on 2020/7/24
 */
public class Keyboard implements ComputerPart {

    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}
