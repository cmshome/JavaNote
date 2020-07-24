package com.lxk.design.pattern.decorator.example0;

/**
 * @author LiXuekai on 2020/7/24
 */
public class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        // 向一个现有的对象添加新的功能，同时又不改变其结构。
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }

    private void setRedBorder(Shape decoratedShape){
        System.out.println("Border Color: Red");
    }
}
