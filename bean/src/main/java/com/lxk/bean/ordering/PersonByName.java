package com.lxk.bean.ordering;


import com.lxk.bean.model.Person;

import java.util.Comparator;

/**
 * 测试model集合按某属性排序
 * <p>
 * @author lxk on 2016/11/25
 */
public class PersonByName implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        //这可以修改来升序或者降序
        //return o2.getName().compareTo(o1.getName());//降序
        return o1.getName().compareTo(o2.getName());//升序
    }
}
