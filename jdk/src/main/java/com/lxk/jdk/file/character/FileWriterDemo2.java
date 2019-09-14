package com.lxk.jdk.file.character;

import java.io.FileWriter;
import java.io.IOException;

/**
 * IO异常的处理方式。
 */
class FileWriterDemo2 {
    public static void main(String[] args) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("demo.txt");
            fw.write("abcdefg");
        } catch (IOException e) {
            System.out.println("catch:" + e.toString());
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        }
    }
}
