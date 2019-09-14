package com.lxk.jdk.file.character;
/*
第二种方式：通过字符数组进行读取。
*/

import java.io.FileReader;
import java.io.IOException;

class FileReaderDemo2 {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("d:demo.txt");
        //定义一个字符数组。用于存储读到字符。
        //该read(char[])返回的是读到字符个数。
        char[] buf = new char[1024];
        int num;
        while ((num = fr.read(buf)) != -1) {
            System.out.println(new String(buf, 0, num));
        }
        fr.close();
    }
}

