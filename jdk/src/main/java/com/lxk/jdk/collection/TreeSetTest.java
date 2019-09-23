package com.lxk.jdk.collection;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.TreeSet;

/**
 * @author LiXuekai on 2019/9/23
 */
public class TreeSetTest {

    /**
     * 按照自然顺序排序了
     */
    @Test
    public void test() {
        TreeSet<Integer> set = Sets.newTreeSet();
        set.add(7);
        set.add(6);
        set.add(5);
        set.add(4);
        set.add(3);
        set.add(2);
        set.add(1);

        //[1, 2, 3, 4, 5, 6, 7]
        System.out.println(set.toString());
    }
}
