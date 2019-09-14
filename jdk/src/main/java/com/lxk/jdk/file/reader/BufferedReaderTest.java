package com.lxk.jdk.file.reader;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BufferedReader test
 *
 * @author LiXuekai on 2019/9/12
 */
public class BufferedReaderTest {

    /**
     * 一行行的读文件，涉及到编码格式。
     * 如果还是有乱码，那就可能是所读读文件的编码格式的问题啦。
     */
    @Test
    public void getMapSetting() {
        String filePath = "";

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("关闭头寸配置文件流出错" + e);
                }
            }
        }
    }
}
