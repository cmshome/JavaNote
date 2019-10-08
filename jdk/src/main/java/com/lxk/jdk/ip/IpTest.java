package com.lxk.jdk.ip;

import com.lxk.jdk.common.Tools;
import org.junit.Test;

import java.net.UnknownHostException;

/**
 * IP在处理的时候，可以转成long类型，然后弄成IP树，方便匹配，提供匹配效率。
 *
 * @author LiXuekai on 2019/10/8
 */
public class IpTest {

    @Test
    public void ip2Long() {
        try {
            long ipLong = Tools.getIpLong("0.0.0.1");
            System.out.println(ipLong);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
