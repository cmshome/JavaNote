package com.lxk.jdk.file.character;

import java.io.FileReader;
import java.io.IOException;

/**
 * 读取一个文件，并打印在控制台上。
 * (测试代码中并没有正确姿势关闭文件流，释放系统资源。)
 */
class FileReaderTest {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("d:ss.txt");
        char[] buf = new char[1024];
        int num;
        while ((num = fr.read(buf)) != -1) {
            System.out.print(new String(buf, 0, num));
        }
        fr.close();
    }
}
