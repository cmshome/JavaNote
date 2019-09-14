package com.lxk.bean.ordering;

import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import com.lxk.bean.model.Person;

import java.text.Collator;
import java.util.Locale;

/**
 * 排序器的存储地方
 *
 * @author lxk on 2018/9/3
 */
public interface OrderingConstants {

    /**
     * 按 age 排序，一定要判断一下对比的对象以及字段为null的情况，不然会bug的，虽然你当时可能不会报错。
     */
    Ordering<Person> AGE_ORDERING = new Ordering<Person>() {
        @Override
        public int compare(Person left, Person right) {
            if (left == null && right == null) {
                return 0;
            }
            if (left == null) {
                return 1;
            }
            if (right == null) {
                return -1;
            }
            //这个地方不要自己去 a - b ，不要自己去算，因为int类型可以自己减少，但是long型可能就炸啦
            //这地方除了Ints，还有Longs，可以看下源码的这个文件夹下的相同效果的类。
            return Ints.compare(left.getAge(), right.getAge());
        }
    };

    /**
     * 按 name 排序，一定要判断一下对比的对象以及字段为null的情况，不然会bug的，虽然你当时可能不会报错。
     */
    Ordering<Person> NAME_ORDERING = new Ordering<Person>() {
        @Override
        public int compare(Person left, Person right) {
            if (left == null && right == null) {
                return 0;
            }
            if (left == null) {
                return 1;
            }
            if (right == null) {
                return -1;
            }
            if (left.getName() == null && right.getName() == null) {
                return 0;
            }
            if (left.getName() == null) {
                return 1;
            }
            if (right.getName() == null) {
                return -1;
            }
            return Collator.getInstance(Locale.CHINA).compare(left.getName(), right.getName());
        }
    };
}
