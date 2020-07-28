package com.lxk.jdk.file.xml;

import com.google.common.collect.Maps;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 解析xml，循环输出所有的k v
 *
 * @author LiXuekai on 2020/7/26
 */
public class XmlTest2 {
    private static final String XML_INFO = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<ROOT type=\"response\">\n" +
            "    <std400trdt>20200715</std400trdt>\n" +
            "    <std400tcsq>0</std400tcsq>\n" +
            "    <std400qsys>" +
            "        <lxk0>100</lxk0>" +
            "        <lxk1>200</lxk1>" +
            "    </std400qsys>\n" +
            "    <std400pgqf>N</std400pgqf>\n" +
            "    <std400ouan/>\n" +
            "    <ESFECT>00000</ESFECT>\n" +
            "    <EMTRCD>1001045</EMTRCD>\n" +
            "    <LIST/>\n" +
            "</ROOT>";

    @Test
    public void parse() throws DocumentException {
        SAXReader sr = new SAXReader();
        Document doc = sr.read(new ByteArrayInputStream(XML_INFO.getBytes(StandardCharsets.UTF_8)));
        Element root = doc.getRootElement();
        getKeyValueInOneMap(root);

    }

    public static void getKeyValueInOneMap(Element element) {
        Map<String, Object> map = Maps.newTreeMap();
        String keyPrefix = "pre";
        forkv0(keyPrefix, map, element);
        //forkv1(element);
        //forkv2(element);
        out(map);
    }

    private static void out(Map<String, Object> map) {
        int index = 0;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(++index + " " + entry.getKey() + "  " + entry.getValue());
        }
    }

    private static void forkv0(String keyPrefix, Map<String, Object> map, Element element) {
        List elements = element.elements();
        for (Object o : elements) {
            try {
                Element e = (Element) o;
                String name = e.getName();
                Object data = e.getData();
                int size = e.elements().size();
                if (size > 0) {
                    String newKey = keyPrefix + "_" + name;
                    forkv0(newKey, map, e);
                } else {
                    map.put(keyPrefix + "_" + name, data);
                }
                //System.out.println("key is " + name + " ; value is " + data + " ; size is " + size);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    private static void forkv2(Element element) {
        System.out.println(element.getName());
        Iterator<Node> it = element.nodeIterator();
        while (it.hasNext()) {
            Node node = it.next();
            //只有标签节点才有子节点 所以判断这个节点是否是标签节点
            if (node instanceof Element) {
                Element element1 = (Element) node;
                forkv2(element1);
            }
        }
    }

    private static void forkv1(Element element) {
        Iterator iterator = element.elementIterator();
        while (iterator.hasNext()) {
            Element next = (Element) iterator.next();
            Object data = next.getData();
            System.out.println(next.getName() + "  " + data);
        }
    }
}
