package com.lxk.lock.readwritelock;

/**
 * @author LiXuekai on 2020/7/17
 */
public class Main {
    public static void main(String[] args) {

        new Thread(() -> {
            int i = 0;
            while (true) {
                String s = String.valueOf(i++);
                Object put = Cache.put(s, s);
                System.out.println(put);
            }
        }).start();

        new Thread(() -> {
            int i = 0;
            while (true) {
                String s = String.valueOf(i++);
                Object get = Cache.get(s);
                System.out.println(get);
            }
        }).start();

        new Thread(() -> {
            int i = 0;
            while (true) {
                String s = String.valueOf(i++);
                Object remove = Cache.remove(s);
                System.out.println(remove);
            }
        }).start();
    }
}
