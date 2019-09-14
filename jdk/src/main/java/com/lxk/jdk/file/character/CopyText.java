package com.lxk.jdk.file.character;

import java.io.*;

/**
 * 复制文件：先从硬盘读到内存，在写到硬盘
 */
class CopyText {
    public static void main(String[] args) throws IOException {
        fileCopyRightWay();
    }

    /**
     * 用缓冲区读写，来提升读写效率。
     */
    private static void fileCopyRightWay() {
        InputStreamReader fr = null;
        OutputStreamWriter fw = null;
        try {
            fr = new InputStreamReader(new FileInputStream("D:MyAssetJob.java"),"UTF-8");//读
            fw = new OutputStreamWriter(new FileOutputStream("e:MyAssetJob.txt"), "UTF-8");//写
            char[] buf = new char[1024];//缓冲区
            int len;
            while ((len = fr.read(buf)) != -1) {
                fw.write(buf, 0, len);//读几个写几个
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

            if (fw != null) {
                try {
                    fw.flush();
                    fw.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     * 未用缓冲区进行读写，读一个字符就写一个字符。
     */
    public static void fileCopyWrongWay() throws IOException {
        //创建目的地。
        FileWriter fw = new FileWriter("D:MyAssetJob.java");
        //与已有文件关联。
        FileReader fr = new FileReader("E:/MyAssetJob.java");

        int ch;
        while ((ch = fr.read()) != -1) {
            fw.write(ch);
        }
        fw.close();
        fr.close();
    }
}
