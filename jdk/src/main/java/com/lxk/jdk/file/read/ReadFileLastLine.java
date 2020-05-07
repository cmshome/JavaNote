package com.lxk.jdk.file.read;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 读文件最后n行
 *
 * @author LiXuekai on 2020/5/7
 */
public class ReadFileLastLine {

    @Test
    public void readLastLine() throws IOException, InterruptedException {
        String fileName = "/Users/fang/Downloads/collector-17.log";
        //while (true){
            String s = readLastRows(fileName, StandardCharsets.UTF_8, 2);
            System.out.println(s);
            //Thread.sleep(1000L);
        //}

    }

    /**
     * 读取文件最后几行
     * 相当于Linux系统中的tail命令 读取大小限制是2GB
     *
     * @param filename 文件名
     * @param charset  文件编码格式,传null默认使用defaultCharset
     * @param rows     读取行数
     * @throws IOException IOException
     */
    public static String readLastRows(String filename, Charset charset, int rows) throws IOException {
        charset = charset == null ? Charset.defaultCharset() : charset;
        String lineSeparator = System.getProperty("line.separator");
        try (RandomAccessFile rf = new RandomAccessFile(filename, "r")) {
            // 每次读取的字节数要和系统换行符大小一致
            byte[] c = new byte[lineSeparator.getBytes().length];
            // 在获取到指定行数和读完文档之前,从文档末尾向前移动指针,遍历文档每一个字节
            for (long pointer = rf.length(), lineSeparatorNum = 0; pointer >= 0 && lineSeparatorNum < rows; ) {
                // 移动指针
                rf.seek(pointer--);
                // 读取数据
                int readLength = rf.read(c);
                if (readLength != -1 && new String(c, 0, readLength).equals(lineSeparator)) {
                    lineSeparatorNum++;
                }
                //扫描完依然没有找到足够的行数,将指针归0
                if (pointer == -1 && lineSeparatorNum < rows) {
                    rf.seek(0);
                }
            }
            byte[] tempBytes = new byte[(int) (rf.length() - rf.getFilePointer())];
            rf.readFully(tempBytes);
            return new String(tempBytes, charset);
        }
    }

}

