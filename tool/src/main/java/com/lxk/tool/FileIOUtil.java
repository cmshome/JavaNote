package com.lxk.tool;

import com.google.common.collect.Lists;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

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

    /**
     * 一行行的读文件
     *
     * @param path            path
     * @param ignoreFirstLine 第一行是标题不是数据，给剔除掉否？。
     * @param charset         charset
     */
    public static List<String> readFileByLine(String path, boolean ignoreFirstLine, String charset) {
        List<String> list = Lists.newArrayList();
        try (Scanner scanner = new Scanner(new File(path), charset)) {
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        int size = list.size();
        return ignoreFirstLine ? list.subList(1, size) : list;

    }

    /**
     * 一行行的读文件
     *
     * @param path            path
     * @param ignoreFirstLine 第一行是标题不是数据，给剔除掉否？。
     */
    public static List<String> readFileByLine(String path, boolean ignoreFirstLine) {
        return readFileByLine(path, ignoreFirstLine, StandardCharsets.UTF_8.name());
    }

    /**
     * 文件重命名
     *
     * @param oldName 旧名字
     * @param newName 新名字
     */
    public static boolean renameFile(String oldName, String newName) {
        File oldFile = new File(oldName);
        return oldFile.renameTo(new File(newName));
    }
}
