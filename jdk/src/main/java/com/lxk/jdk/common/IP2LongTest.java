package com.lxk.jdk.common;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * IP和Long之间的转换
 *
 * @author LiXuekai on 2019/5/5
 */
public class IP2LongTest {
    public static void main(String[] args) throws UnknownHostException {
        //String src = "188.188.8.118";
        //String des = "10.188.16.110";
        //ip2Long(src);
        //System.out.println("3166439542");
        //
        //ip2Long(des);
        //System.out.println("180097134");

        //System.out.println("3166439542");
        //String srcIp = "188.188.8.188";
        //ip2Long(srcIp);
        //System.out.println(ipToLong(srcIp));
        //System.out.println(ipToLong2((srcIp)));
        //System.out.println(Tools.getIpLong(srcIp));
        //
        //String desIp = "188.188.8.170";
        //System.out.println("3166439594");
        //ip2Long(desIp);
        //System.out.println(ipToLong(desIp));
        //System.out.println(ipToLong2(desIp));
        //System.out.println(Tools.getIpLong(desIp));




    }



    private static void ip2Long(String ip) {
        Long ips = 0L;
        String[] numbers = ip.split("\\.");
        //等价上面
        for (int i = 0; i < 4; ++i) {
            ips = ips << 8 | Integer.parseInt(numbers[i]);
        }
        System.out.println(ips + "---");
    }

    public static long ipToLong2(String strIp){
        long[] ip = new long[4];
        //先找到IP地址字符串中.的位置
        int position1 = strIp.indexOf(".");
        int position2 = strIp.indexOf(".", position1 + 1);
        int position3 = strIp.indexOf(".", position2 + 1);
        //将每个.之间的字符串转换成整型
        ip[0] = Long.parseLong(strIp.substring(0, position1));
        ip[1] = Long.parseLong(strIp.substring(position1+1, position2));
        ip[2] = Long.parseLong(strIp.substring(position2+1, position3));
        ip[3] = Long.parseLong(strIp.substring(position3+1));
        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
    }

    private static Long ipToNumber(String ip) {
        Long ips = 0L;
        String[] numbers = ip.split("\\.");
        //等价上面
        for (int i = 0; i < 4; ++i) {
            ips = ips << 8 | Integer.parseInt(numbers[i]);
        }
        return ips;
    }

    public static long ipToLong(String ip) {
        String[] ipArray = ip.split("\\.");
        List ipNums = new ArrayList();
        for (int i = 0; i < 4; ++i) {
            ipNums.add(Long.valueOf(Long.parseLong(ipArray[i].trim())));
        }
        long ZhongIPNumTotal = ((Long) ipNums.get(0)).longValue() * 256L * 256L * 256L
                + ((Long) ipNums.get(1)).longValue() * 256L * 256L + ((Long) ipNums.get(2)).longValue() * 256L
                + ((Long) ipNums.get(3)).longValue();

        return ZhongIPNumTotal;
    }
}
