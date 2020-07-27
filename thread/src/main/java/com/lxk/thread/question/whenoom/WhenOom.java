package com.lxk.thread.question.whenoom;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * JVM 堆内存溢出后，其他线程是否可继续工作
 *
 * @author LiXuekai on 2020/7/27
 */
public class WhenOom {

    public static void main(String[] args) {

        new Thread(() -> {
            List<byte[]> list = new ArrayList<>();
            int i = 0;
            while (true) {
                System.out.println(new Date().toString() + " ---" + i++ + " "+ Thread.currentThread().getName());
                byte[] b = new byte[1024 * 1024 * 1];
                list.add(b);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 线程二
        new Thread(() -> {
            int i = 0;
            while (true) {
                System.out.println(new Date().toString() + " " + i++ + " " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
