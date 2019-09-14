package com.lxk.jdk.file.properties;

import org.junit.Test;

import java.io.*;
import java.util.Properties;

/**
 * 读写properties文件测试
 *
 * @author lxk on 2017/4/25
 */
public class PropertiesTest {

    @Test
    public void main() {
        Properties prop = readPropertiesFile();
        PropertiesOrderTest.printProp(prop);
        writePropertiesFile(prop);
    }

    /**
     * 读Properties文件
     */
    private static Properties readPropertiesFile() {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream("src/main/resources/properties/order.properties"));
            //prop.load(in);//直接这么写，如果properties文件中有汉子，则汉字会乱码。因为未设置编码格式。
            properties.load(new InputStreamReader(inputStream, "utf-8"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return properties;
    }

    /**
     * 写Properties文件
     */
    private static void writePropertiesFile(Properties properties) {
        properties.setProperty("phone", "10086");
        FileOutputStream fileOutputStream = null;
        try {
            //保存属性到b.properties文件
            //true表示追加打开,false每次都是清空再重写
            fileOutputStream = new FileOutputStream("b.properties", false);
            //prop.store(oFile, "此参数是保存生成properties文件中第一行的注释说明文字");//这个会两个地方乱码
            //prop.store(new OutputStreamWriter(oFile, "utf-8"), "汉字乱码");//这个就是生成的properties文件中第一行的注释文字乱码
            properties.store(new OutputStreamWriter(fileOutputStream, "utf-8"), "lll");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

}
