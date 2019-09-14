package com.lxk.jdk.file.character;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 演示对已有文件的数据续写。
 */
class FileWriterDemo3 {
    public static void main(String[] args) throws IOException {

        //传递一个true参数，代表不覆盖已有的文件。并在已有文件的末尾处进行数据续写。
        FileWriter fw = new FileWriter("demo.txt", true);

        fw.write("nihao\r\nxiexie");

        fw.close();
    }
}
