package com.lxk.guava.collection;

import com.google.common.base.Joiner;
import com.google.common.primitives.Ints;
import org.junit.Test;

/**
 * Joiner test
 * @author LiXuekai on 2019/10/25
 */
public class JoinerTest {

    @Test
    public void test(){
        int[] numbers = { 1, 2, 3, 4, 5 };
        String numbersAsString = Joiner.on(";").join(Ints.asList(numbers));
        System.out.println(numbersAsString);
    }
}
