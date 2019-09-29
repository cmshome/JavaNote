package com.lxk.guava.collection;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.junit.Test;

/**
 * Table有以下实现：
 * 　　HashBasedTable：基于HashMap<R,HashMap<C,V>>HashMap<R,HashMap<C,V>>的实现。
 * 　　TreeBasedTable：基于TreeMap<R,TreeMap<C,V>>TreeMap<R,TreeMap<C,V>>的实现。
 * 　　ImmutableTable：基于ImmutableMap<R,ImmutableMap<C,V>>ImmutableMap<R,ImmutableMap<C,V>>的实现。
 *
 * @author LiXuekai on 2019/9/20
 */
public class TableTest {

    /**
     * 类似一个坐标轴，由x轴和y轴2个值去get value。
     */
    @Test
    public void test() {
        Table<Integer, Integer, Integer> table = HashBasedTable.create();
        table.put(1,1,1);
        System.out.println(table.get(1,1));
    }
}
