package com.lxk.jdk.file.nio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Java NIO 的例子
 * 转载文章链接地址：
 * https://blog.csdn.net/qq_27093465/article/details/79657434
 *
 * @author lxk on 2018/3/22
 */
public class ChannelTest {
    public static void main(String[] args) {
        File file = new File("D:test/lxk_data.txt");
        FileOutputStream outputStream = null;
        FileChannel channel = null;
        try {
            outputStream = new FileOutputStream(file);
            channel = outputStream.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            String string = "java nio ------";
            buffer.put(string.getBytes());
            //此处必须要调用buffer的flip方法
            buffer.flip();
            channel.write(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (channel != null) {
                channel.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
