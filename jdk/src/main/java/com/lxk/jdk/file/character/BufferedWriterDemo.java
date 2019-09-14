package com.lxk.jdk.file.character;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class BufferedWriterDemo {
    public static void main(String[] args) {
        BufferedWriter bufw = null;
        try {
            //创建一个字符写入流对象。
            FileWriter fw = new FileWriter("buf.txt");
            //为了提高字符写入流效率。加入了缓冲技术。
            //只要将需要被提高效率的流对象作为参数传递给缓冲区的构造函数即可。
            bufw = new BufferedWriter(fw);
            for (int x = 1; x < 5; x++) {
                bufw.write("abcd" + x);
                bufw.newLine();
                bufw.flush();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            //关闭缓冲区，缓冲区关闭其实就是其中的流对象的关闭，所以流资源已经不需要关闭啦。
            if (bufw != null) {
                try {
                    bufw.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}