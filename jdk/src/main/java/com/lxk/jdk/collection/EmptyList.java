package com.lxk.jdk.collection;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

/**
 * 测试 Collections.emptyList() 所得到的集合的操作
 * <p>
 *
 * @author lxk on 2017/3/23
 */
public class EmptyList {

    @Test
    public void main() {
        List<String> list = Collections.emptyList();
        System.out.println(list.size());
        System.out.println(list.toString());
        for (String s : list) {
            System.out.println(s);
        }
        try {
            //异常
            list.add("");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}

