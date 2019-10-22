package com.lxk.jdk.file.scanner;

import com.google.common.collect.Lists;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * scanner test
 *
 * @author LiXuekai on 2019/9/11
 */
public class ScannerTest {


    public static void main(String[] args) {
        String s = readFile3("/Users/fang/Downloads/response.xml");

        SAXReader reader = new SAXReader();
        reader.setEncoding("utf-8");
        try {
            //Document read = reader.read(new ByteArrayInputStream(s.getBytes("utf-8")));
            Document read = DocumentHelper.parseText(s);

            Element rootElement = read.getRootElement();
            Element SysHead = rootElement.element("SysHead");
            Element retInfArry = SysHead.element("RetInfArry");
            String retCd = retInfArry.element("RetCd").getStringValue();
            System.out.println(retCd);

            Element body = rootElement.element("Body");
            System.out.println(body.element("LogonNm").getStringValue());

        } catch (Exception e) {
            e.printStackTrace();
        }


        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        String bitLength = get8BitLength(bytes.length + "");
        System.out.println(bitLength + s);

    }
    private static String readFile3(String path) {
        StringBuilder stringBuilder = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(path), StandardCharsets.UTF_8.name())) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                stringBuilder.append(line);
            }
        } catch (Exception e) {
        }
        return stringBuilder.toString();
    }

    @SuppressWarnings("unchecked")
    public static String get8BitLength(String length) {
        int expectLength = 8;
        int count = length.length();
        int needAdd = expectLength - count;
        StringBuilder stringBuilder = new StringBuilder();
        List<String> list = Lists.newArrayList();
        if (needAdd > 0) {
            list = new ArrayList(Collections.nCopies(needAdd, "0"));
        }
        if (!list.isEmpty()) {
            list.forEach(stringBuilder::append);
        }
        String s = stringBuilder.toString() + length;
        System.out.println(s);
        return s;
    }

    @Test
    public void read() {
        try {
            String path = System.getProperty("user.dir") + "/src/main/resources/source";
            System.out.println(path);
            File file = new File(path);
            String[] fileArray = file.list();
            if (fileArray == null) {
                return;
            }
            for (String fileName : fileArray) {
                List<String> list = readFile(path + "/" + fileName);
                List<List<String>> all = Lists.newArrayListWithExpectedSize(list.size());
                String split = "\u001C";
                //String split = "\u001C\u001D";
                //String split = "\u001C\u001D";
                list.forEach(s1 -> all.add(Lists.newArrayList(s1.split(split))));
                all.forEach(strings -> {
                    strings.forEach(s ->  System.out.print(s + " "));
                    System.out.println();
                });
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void readAndWrite() {
        try {
            String s = System.getProperty("user.dir") + "/src/main/resources/source";
            File file = new File(s);
            String[] fileArray = file.list();
            if (fileArray == null) {
                return;
            }
            for (String fileName : fileArray) {
                List<String> list = readFile(s + "/" + fileName);
                write2File(list, fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private List<String> readFile(String path) {
        List<String> list = Lists.newArrayList();
        try (Scanner scanner = new Scanner(new File(path), "gbk")) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                list.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private static void write2File(List<String> text, String name) {
        PrintWriter pw = null;
        try {
            final File file = new File(System.getProperty("user.dir") + "/src/main/resources/target/" + name);
            FileWriter fw = new FileWriter(file, true);
            pw = new PrintWriter(fw);
            for (String s : text) {
                pw.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.flush();
                pw.close();
            }
        }
    }
}
