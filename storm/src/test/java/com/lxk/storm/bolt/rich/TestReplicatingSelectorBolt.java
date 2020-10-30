package com.lxk.storm.bolt.rich;

import com.lxk.storm.bolt.BaseBoltTest;
import org.junit.Before;
import org.junit.Test;

/**
 * @author LiXuekai on 2020/10/30
 */
public class TestReplicatingSelectorBolt extends BaseBoltTest {

    @Before
    public void init() {
        TOPOLOGY_NAME = "replicate-selector--topology";
    }

    @Test
    public void test() {

    }
}
