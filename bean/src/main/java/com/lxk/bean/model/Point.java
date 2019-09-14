package com.lxk.bean.model;

import lombok.Data;

/**
 * 坐标model 测试泛型用
 * 希望泛型类型只能是某一部分类型，你会希望是Number或其子类类型。这个想法其实就是给泛型参数添加一个界限。
 *
 * @author lxk on 2017/6/14
 */
@Data
public class Point<T extends Number> {
    private T x;
    private T y;

    public Point(T x, T y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
