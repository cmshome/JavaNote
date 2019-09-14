package com.lxk.jdk.file.getresource;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * getResource()和getResourceAsStream以及路径问题
 *
 * @author lxk on 2018/1/11
 */
public class GetResourceAsStreamTest {
    public static void main(String[] args) {
        test();
    }

    @Test
    private static void test() {
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = getInputStream();
            InputStreamReader fr = new InputStreamReader(inputStream, "utf-8");
            bufferedReader = new BufferedReader(fr);
            String line;
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     * 不以’/'开头时默认是从此类所在的包下取资源，
     * 以’/'开头则是从ClassPath根下获取。
     */
    private static InputStream getInputStream() {
        return GetResourceAsStreamTest.class.getResourceAsStream("my.txt");
    }
}
