package com.lxk.jdk.file.xml;

import com.google.common.collect.Lists;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * 1,Document read = reader.read(new ByteArrayInputStream(s.getBytes("utf-8")));
 * 2,Document read = DocumentHelper.parseText(s);
 * 3,Document read = reader.read(new File("src/main/resources/xml/testConfig.xml"));
 *
 * @author lxk on 2016/11/10
 */
public class XmlTest {

    /**
     * 测试解析xml文件
     */
    @Test
    @SuppressWarnings("unchecked")
    public void xmlTest() {
        SAXReader reader = new SAXReader();
        reader.setEncoding(StandardCharsets.UTF_8.toString());
        Document document;
        try {
            document = reader.read(new File("src/main/resources/xml/testConfig.xml"));
            //得到xml跟标签，此处是<root></root>
            Element root = document.getRootElement();
            List<Element> list = root.elements("stream");
            //得到xml文件的配置信息
            List<XmlModel> xmlNodeList = Lists.newArrayList();
            for (Element e : list) {
                List<String> streamIds = Arrays.asList(e.element("streamId").getStringValue().split(","));
                XmlModel temp = new XmlModel(streamIds, e.element("name").getStringValue());
                xmlNodeList.add(temp);
            }
            for (XmlModel xmlModel : xmlNodeList) {
                System.out.println(xmlModel);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }
}
