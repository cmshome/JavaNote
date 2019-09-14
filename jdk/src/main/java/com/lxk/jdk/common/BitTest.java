package com.lxk.jdk.common;

import org.junit.Test;

/**
 * bit中文名称是位，音译“比特”，是用以描述电脑数据量的最小单位。
 * 二进制数系统中，每个0或1就是一个位(bit)。bit 来自binary digit (二进制数字)
 * 1Byte=8bit
 * 1KB=1024Byte(字节)=8*1024bit
 * 1MB=1024KB
 * 1GB=1024MB
 * 1TB=1024GB
 *
 * @author lxk on 2018/3/21
 */
public class BitTest {

    /**
     * 输出各种基础类型的位数bit大小,也就是二进制讲的话，所占的位数。1Byte=8bit
     */
    @Test
    public void getBit() {
        //The number of bits used to represent a {@code byte} value in two's complement binary form.
        //用来表示Byte类型的值的位数，说到底，就是bit的个数，也就是二进制的位数。
        System.out.println("Byte: " + Byte.SIZE);
        System.out.println("Short: " + Short.SIZE);
        System.out.println("Character: " + Character.SIZE);
        System.out.println("Integer: " + Integer.SIZE);
        System.out.println("Float: " + Float.SIZE);
        System.out.println("Long: " + Long.SIZE);
        System.out.println("Double: " + Double.SIZE);
        System.out.println("Boolean: " + Boolean.toString(false));
    }
}
