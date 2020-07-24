package com.lxk.design.pattern.visitor;

/**
 * 鼠标
 *
 * @author LiXuekai on 2020/7/24
 */
public class Mouse implements ComputerPart {

    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}
