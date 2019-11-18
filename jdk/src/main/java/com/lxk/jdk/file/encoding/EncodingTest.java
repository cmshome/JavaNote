package com.lxk.jdk.file.encoding;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 针对读写文件经常遇到乱码的问题，做个综合测试文件，方便快速搞定问题。
 *
 * @author lxk on 2018/1/11
 */
public class EncodingTest {
    public static void main(String[] args) {
        fileEncoding();
    }

    private static void fileEncoding() {
        InputStreamReader inputStreamReader = null;
        try {
            //......src\main\resources\template.json
            InputStream is = EncodingTest.class.getResourceAsStream("/template.json");
            InputStream inputStream = new BufferedInputStream(new FileInputStream("D:es-source_未修改.properties"));
            //prop.load(in);//直接这么写，如果properties文件中有汉子，则汉字会乱码。因为未设置编码格式。
            inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            bufferedReader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }
}
