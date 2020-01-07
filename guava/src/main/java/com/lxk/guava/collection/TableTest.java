package com.lxk.guava.collection;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;
import org.junit.Test;

import java.util.*;

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

    @Test
    public void tt() {
        TreeBasedTable<String, String, String> table = TreeBasedTable.create();
        table.put("x", "y1", "z1");
        table.put("x", "y2", "z2");
        table.put("x", "y3", "z3");
        Iterator<String> iterator = table.rowKeySet().iterator();
        Map<String, String> removeMap = Maps.newHashMap();
        while (iterator.hasNext()){
            String row = iterator.next();
            SortedMap<String, String> rowMap = table.row(row);
            for (String column : rowMap.keySet()) {
                String s1 = rowMap.get(column);
                if("y2".equals(column)){
                    removeMap.put(row, column);
                }
            }
        }

        System.out.println(table.toString());
        System.out.println(removeMap);
        for (Map.Entry<String, String> entry : removeMap.entrySet()) {
            table.remove(entry.getKey(), entry.getValue());
        }
        System.out.println(table.toString());


        System.out.println("--------");


        System.out.println(table);
        System.out.println(table.size());
        SortedSet<String> strings = table.rowKeySet();
        System.out.println(strings);

        Collection<String> values = table.values();
        System.out.println(values);

        SortedMap<String, Map<String, String>> stringMapSortedMap = table.rowMap();
        System.out.println(stringMapSortedMap);

        Set<Table.Cell<String, String, String>> cells = table.cellSet();
        System.out.println(cells);


        // y轴的值 set    [y1, y2, y3]
        Set<String> set = table.columnKeySet();
        System.out.println(set);


        String remove = table.remove("x", "ssy");
        System.out.println("remove " + remove);
        System.out.println(table.size());


        SortedMap<String, String> x = table.row("x");
        String firstKey = x.firstKey();
        System.out.println(x.get(firstKey));
        for (String s : x.keySet()) {
            System.out.println(s);
        }
        System.out.println();
        String y = x.get("y");
        System.out.println(y);

        System.out.println(table.get("x","y"));
    }
}
