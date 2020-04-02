package com.lxk.jdk.collection;

import com.google.common.collect.Lists;
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

    @Test
    public void test(){
        List<String> list = Lists.newArrayList();
        for (String s : list) {
            System.out.println(s);
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        // IndexOutOfBoundsException 异常
        String s = list.get(0);
    }
}

