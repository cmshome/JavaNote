package com.lxk.bean.ordering;


import com.lxk.bean.model.Person;

import java.util.Comparator;

/**
 * 测试model集合按某属性排序
 * <p>
 * @author lxk on 2016/11/25
 */
public class PersonByAge implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        //这可以修改来升序或者降序
        //return o2.getAge() - o1.getAge();//降序
        return o1.getAge() - o2.getAge();//升序
    }
}
