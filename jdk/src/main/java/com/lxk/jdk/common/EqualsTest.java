package com.lxk.jdk.common;

import com.google.common.collect.Lists;
import com.lxk.bean.model.ColumnMetaData;
import com.lxk.bean.model.TableMetaData;
import org.junit.Test;

import java.util.Collection;

/**
 * 测试equals的各种情况
 *
 * @author lxk on 2018/9/17
 */
public class EqualsTest {

    /**
     * 比较2个集合是否是相同的，不考虑集合的顺序，只要里面的元素确实相同即可。
     */
    @Test
    public void testCollectionEqual() {
        ColumnMetaData c1 = new ColumnMetaData("age1", "name", true);
        ColumnMetaData c2 = new ColumnMetaData("age2", "name", true);
        ColumnMetaData c3 = new ColumnMetaData("age3", "name", true);
        ColumnMetaData c4 = new ColumnMetaData("age4", "name", true);
        Collection<ColumnMetaData> list1 = Lists.newArrayList(c1, c2, c3);
        Collection<ColumnMetaData> list2 = Lists.newArrayList(c2, c3, c1);
        TableMetaData table1 = new TableMetaData(list1);
        TableMetaData table2 = new TableMetaData(list2);
        boolean equals = table1.equals(table2);
        System.out.println(equals);
    }

}
