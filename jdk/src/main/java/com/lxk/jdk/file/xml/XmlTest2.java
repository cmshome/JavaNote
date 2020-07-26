package com.lxk.jdk.file.xml;

import com.google.common.collect.Maps;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author LiXuekai on 2020/7/26
 */
public class XmlTest2 {
    private static final String XML_INFO = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<ROOT type=\"response\">\n" +
            "    <std400mgid>AAAAAAA</std400mgid>\n" +
            "    <stdrtninfo>处理成功</stdrtninfo>\n" +
            "    <stdrespno>DCBS</stdrespno>\n" +
            "    <stdmsgtype>0210</stdmsgtype>\n" +
            "    <stdprocode>1001045</stdprocode>\n" +
            "    <stdbankno>001</stdbankno>\n" +
            "    <std400usno>8164</std400usno>\n" +
            "    <stdtranins>732841</stdtranins>\n" +
            "    <std400aqid>PWAP</std400aqid>\n" +
            "    <stdtermtrc>PWAP20200715430697015999</stdtermtrc>\n" +
            "    <std400trdt>20200715</std400trdt>\n" +
            "    <std400tcsq>0</std400tcsq>\n" +
            "    <std400qsys>" +
            "        <lxk0>100</lxk0>" +
            "        <lxk1>200</lxk1>" +
            "    </std400qsys>\n" +
            "    <std400pgqf>N</std400pgqf>\n" +
            "    <std400pgtk/>\n" +
            "    <std400pgsn/>\n" +
            "    <std400pgoq>1</std400pgoq>\n" +
            "    <std400pgts/>\n" +
            "    <std400pgtn>0</std400pgtn>\n" +
            "    <std400miqt>0</std400miqt>\n" +
            "    <stdrefnum>031199815484</stdrefnum>\n" +
            "    <std400acur>00</std400acur>\n" +
            "    <std400jrno>SC220233099396</std400jrno>\n" +
            "    <std400actn>0000000000022</std400actn>\n" +
            "    <std400rvac>6217680502869253</std400rvac>\n" +
            "    <std400acsq>000001</std400acsq>\n" +
            "    <std400cunm>周波</std400cunm>\n" +
            "    <std400oabr>732841</std400oabr>\n" +
            "    <stdcyno>001</stdcyno>\n" +
            "    <std400byfg/>\n" +
            "    <std400tram>10000.00</std400tram>\n" +
            "    <std400oubl>325279.58</std400oubl>\n" +
            "    <std400rzac>6217680508841637</std400rzac>\n" +
            "    <std400insq>000001</std400insq>\n" +
            "    <stkessbnfg/>\n" +
            "    <std400etnm>周向阳</std400etnm>\n" +
            "    <stdessbsin>732801</stdessbsin>\n" +
            "    <std400etbl>232035.14</std400etbl>\n" +
            "    <std400elno/>\n" +
            "    <std400ouam/>\n" +
            "    <std400uanm/>\n" +
            "    <std400iuac/>\n" +
            "    <std400ouan/>\n" +
            "    <ESFECT>00000</ESFECT>\n" +
            "    <EMTRCD>1001045</EMTRCD>\n" +
            "    <LIST/>\n" +
            "</ROOT>";

    @Test
    public void parse() throws UnsupportedEncodingException, DocumentException {
        SAXReader sr = new SAXReader();
        Document doc = sr.read(new ByteArrayInputStream(XML_INFO.getBytes("utf-8")));
        Element root = doc.getRootElement();
        getChildNodes(root);

    }

    public static void getChildNodes(Element element) {
        List attributes = element.attributes();
        Map<String, Object> map = Maps.newHashMap();
        String keyPrifix = "trans_ref";
        forkv0(keyPrifix, map, element);
        //forkv1(element);
        //forkv2(element);
        out(map);

    }

    private static void out(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "  " + entry.getValue());
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
                getChildNodes(element1);
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
