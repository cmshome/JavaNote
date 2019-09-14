package com.lxk.jdk.file.properties;

import org.junit.Test;

import java.io.*;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 读写properties文件测试（带顺序的读和写）
 * <p>
 * @author lxk on 2017/5/2
 */
public class PropertiesOrderTest {

    @Test
    public void main() {
        Properties prop = readOrderedPropertiesFile();
        trimSpace(prop);
        printProp(prop);
        writeOrderedPropertiesFile(prop);
    }


    /**
     * 读取properties文件的值中会可能会有空格，需要处理一下。
     */
    private static void trimSpace(Properties prop) {
        for (Map.Entry<Object, Object> entry : prop.entrySet()) {
            entry.setValue(entry.getValue().toString().trim());
        }
    }

    /**
     * 输出properties的key和value
     */
    static void printProp(Properties properties) {
        System.out.println("---------（方式一）------------");
        for (String key : properties.stringPropertyNames()) {
            System.out.println(key + "=" + properties.getProperty(key));
        }

        System.out.println("---------（方式二）------------");
        //返回属性key的集合
        Set<Object> keys = properties.keySet();
        for (Object key : keys) {
            System.out.println(key.toString() + "=" + properties.get(key));
        }

        System.out.println("---------（方式三）------------");
        //返回的属性键值对实体
        Set<Map.Entry<Object, Object>> entrySet = properties.entrySet();
        for (Map.Entry<Object, Object> entry : entrySet) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }

        System.out.println("---------（方式四）------------");
        Enumeration<?> e = properties.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = properties.getProperty(key);
            System.out.println(key + "=" + value);
        }
    }

    /**
     * 读Properties文件（有序）
     */
    private static Properties readOrderedPropertiesFile() {
        Properties properties = new OrderedProperties();
        InputStreamReader inputStreamReader = null;
        try {
            //es-source
            InputStream inputStream = new BufferedInputStream(new FileInputStream("src/main/resources/properties/order.properties"));
            //prop.load(in);//直接这么写，如果properties文件中有汉子，则汉字会乱码。因为未设置编码格式。
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            properties.load(inputStreamReader);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return properties;
    }

    /**
     * 写Properties文件（有序）
     */
    private static void writeOrderedPropertiesFile(Properties properties) {
        properties.setProperty("phone", "10086");
        OutputStreamWriter outputStreamWriter = null;
        try {
            //保存属性到b.properties文件
            String filePath = "src/main/resources/properties";
            File file = new File(filePath);
            if(!file.exists()){
                boolean result = file.mkdirs();
                if (!result) {
                    return ;
                }
            }

            //true表示追加打开,false每次都是清空再重写
            FileOutputStream fileOutputStream = new FileOutputStream(filePath + "/es-source-lxk-test.properties", false);
            //prop.store(oFile, "此参数是保存生成properties文件中第一行的注释说明文字");//这个会两个地方乱码
            //prop.store(new OutputStreamWriter(oFile, "utf-8"), "汉字乱码");//这个就是生成的properties文件中第一行的注释文字乱码
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
            properties.store(outputStreamWriter, "lll");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (outputStreamWriter != null) {
                try {
                    outputStreamWriter.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }
}
