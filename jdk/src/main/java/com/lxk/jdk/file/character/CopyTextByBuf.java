package com.lxk.jdk.file.character;

import java.io.*;

/**
 * 通过缓冲区复制一个文件
 */
class CopyTextByBuf {
    public static void main(String[] args) {
        //读文件流的缓存
        BufferedReader bufr = null;
        //写文件流的缓存
        BufferedWriter bufw = null;

        try {
            bufr = new BufferedReader(new FileReader("D:/BufferedWriterDemo.java"));
            bufw = new BufferedWriter(new FileWriter("E:/BufferedWriterDemo.txt"));

            String line;//读缓存和写缓存之间交流的桥梁
            while ((line = bufr.readLine()) != null) {
                bufw.write(line);
                bufw.newLine();//写文件时，添加换行符。为什么呢?看下面解释。
                bufw.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("读写失败");
        } finally {
            try {
                if (bufr != null) {
                    bufr.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            try {
                if (bufw != null) {
                    bufw.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
