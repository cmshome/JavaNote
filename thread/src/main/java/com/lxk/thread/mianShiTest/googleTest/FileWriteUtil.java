package com.lxk.thread.mianShiTest.googleTest;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 此类，是四个线程共享的，
 * <p>
 * @author lxk on 2017/7/14
 */
public class FileWriteUtil {
    private int currentThreadNum = 1;
    /**
     * 记录将字符写入文件的次数
     */
    private int count = 0;

    private String currentFileName;

    public void write(char value, int threadNum) {
        getCurrentFileName();
        FileWriter writer = null;
        try {
            //生成文件位置
            writer = new FileWriter("D:/test/test/" + currentFileName + ".txt", true);
            writer.write(value + " ");
            System.out.printf(
                    "ThreadNum=%d is executing. %c is written into file file%s.txt \n",
                    currentThreadNum, value, currentFileName);
            writer.flush();
            //System.out.println(count);//
            count++;
            currentThreadNum = threadNum;
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (null != writer) {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        getNextThreadNum();
    }

    public int getCurrentThreadNum() {
        return currentThreadNum;
    }

    public void setCurrentThreadNum(int currentThreadNum) {
        this.currentThreadNum = currentThreadNum;
    }

    /**
     * 根据写的次数，判断该写哪个文件了？A，B,C,D.
     */
    private void getCurrentFileName() {
        int temp = count % 4;
        switch (temp) {
            case 0:
                currentFileName = "A";
                break;
            case 1:
                currentFileName = "B";
                break;
            case 2:
                currentFileName = "C";
                break;
            case 3:
                currentFileName = "D";
                break;
            default:
                currentFileName = "E";
        }
    }

    private void getNextThreadNum() {
        if (count % 4 == 0) {
            if (currentThreadNum < 3) {
                currentThreadNum += 2;
            } else {
                currentThreadNum = (currentThreadNum + 2) % 4;
            }
        } else {
            if (currentThreadNum == 4) {
                currentThreadNum = 1;
            } else {
                currentThreadNum++;
            }
        }
    }
}
