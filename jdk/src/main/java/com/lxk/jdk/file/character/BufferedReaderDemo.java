package com.lxk.jdk.file.character;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 一行一行的读取文件到内存中，然后打印输出到控制台
 */
class BufferedReaderDemo {
    public static void main(String[] args) {
        BufferedReader bufr = null;
        try {
            //创建一个字符读取流对象和文件相关联。
            FileReader fr = new FileReader("buf.txt");
            //为了提高效率。加入缓冲技术。将字符读取流对象作为参数传递给缓冲对象的构造函数。
            bufr = new BufferedReader(fr);
            String line;
            try {
                while ((line = bufr.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            if (bufr != null) {
                try {
                    bufr.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }


    }

}

