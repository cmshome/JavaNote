package com.lxk.design.pattern.visitor;

/**
 * @author LiXuekai on 2020/7/24
 */
public interface ComputerPartVisitor {
    void visit(Computer computer);

    void visit(Mouse mouse);

    void visit(Keyboard keyboard);

    void visit(Monitor monitor);
}
