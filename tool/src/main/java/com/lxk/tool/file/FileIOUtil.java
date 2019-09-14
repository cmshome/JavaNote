package com.lxk.tool.file;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * FileIOUtil
 *
 * @author lxk
 */
public class FileIOUtil {

    public static String readFile(String fileName, String charset) throws IOException {
        File f = new File(fileName);
        try (RandomAccessFile readStream = new RandomAccessFile(f, "r")) {
            byte[] buffer = new byte[(int) f.length()];
            readStream.read(buffer, 0, buffer.length);
            return new String(buffer, charset);
        }
    }

    public static void writeFile(String fileName, String text, String charset) throws IOException {
        File f = new File(fileName);
        try (RandomAccessFile readStream = new RandomAccessFile(f, "rw")) {
            readStream.setLength(0);
            byte[] buffer = text.getBytes(charset);
            readStream.write(buffer, 0, buffer.length);
        }
    }
}
