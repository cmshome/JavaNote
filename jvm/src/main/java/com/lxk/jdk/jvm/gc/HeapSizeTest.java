package com.lxk.jdk.jvm.gc;

import java.text.DecimalFormat;

/**
 * 在 jdk1.8 前提下测试堆里面的各区间的大小
 * 参考 jps和jstat2个jdk提供的内存监控工具。
 *
 * @author LiXuekai on 2020/6/5
 */
public class HeapSizeTest {


    /**
     * 命令：jinfo -flags 64363
     * 输出：
     * Attaching to process ID 64363, please wait...
     * Debugger attached successfully.
     * Server compiler detected.
     * JVM version is 25.131-b11
     *
     * Non-default VM flags: -XX:+AlwaysPreTouch -XX:CICompilerCount=4 -XX:CMSInitiatingOccupancyFraction=75
     * -XX:+HeapDumpOnOutOfMemoryError
     * -XX:InitialHeapSize=4294967296 -XX:MaxHeapSize=4294967296
     * -XX:MaxNewSize=697892864
     * -XX:MaxTenuringThreshold=6 -XX:MinHeapDeltaBytes=196608
     * -XX:NewSize=697892864
     * -XX:OldPLABSize=16
     * -XX:OldSize=3597074432
     * -XX:ThreadStackSize=1024
     * -XX:+UseCMSInitiatingOccupancyOnly -XX:+UseCompressedClassPointers
     * -XX:+UseCompressedOops -XX:+UseConcMarkSweepGC -XX:+UseFastUnorderedTimeStamps
     * -XX:+UseParNewGC
     *
     * Command line:  -XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=75 -XX:+UseCMSInitiatingOccupancyOnly
     * -XX:+AlwaysPreTouch -Xss1m -Djava.awt.headless=true -Dfile.encoding=UTF-8 -Djna.nosys=true
     * -Djdk.io.permissionsUseCanonicalPath=true -Dio.netty.noUnsafe=true -Dio.netty.noKeySetOptimization=true
     * -Dio.netty.recycler.maxCapacityPerThread=0 -Dlog4j.shutdownHookEnabled=false -Dlog4j2.disable.jmx=true
     * -Dlog4j.skipJansi=true -XX:+HeapDumpOnOutOfMemoryError
     * -Xms4g
     * -Xmx4g
     * -Des.path.home=/home/lxk/elasticsearch
     *
     *
     * 命令：jstat -gc 64363
     * 输出：
     *  S0C    S1C      S0U    S1U       EC       EU        OC         OU           MC     MU    CCSC   CCSU    YGC     YGCT    FGC    FGCT     GCT
     * 68096.0 68096.0  0.0   16160.3   545344.0  397868.8 3512768.0  2109826.5  71216.0 66507.1 9304.0 8046.9  22389  942.637  270    15.966  958.603
     *
     */
    public static void main(String[] args) {

        // jps 出来的jvm参数，这三个的单位是 byte 字节
        long maxHeapSize = 4294967296L,
                newSize = 697892864L,
                oldSize = 3597074432L;

        System.out.println("InitialHeapSize = MaxHeapSize = " + showNumberBetter(maxHeapSize));

        long all = newSize + oldSize;
        System.out.println("newSize + oldSize = " + showNumberBetter(all));

        System.out.println("newSize + oldSize == maxHeapSize is true.");
        System.out.println("NewSize is " + showNumberBetter(newSize));
        System.out.println("OldSize is " + showNumberBetter(oldSize));
        // jstat 出来的jvm参数 下面的这些个的但是 k bytes KB
        long s0 = 68096L,
                s1 = 68096L,
                eden = 545344L,
                old = 3512768L,
                mc = 71216,
                ccs = 9304;
        long young = s0 + s1 + eden;
        System.out.println("s0 + s1 + eden = " + showNumberBetter(young));

        long heap = young + old;
        System.out.println("young + old = " + showNumberBetter(heap));

        long total = heap + mc + ccs;
        System.out.println("heap + mc + ccs = " + showNumberBetter(total));


    }

    private static String showNumberBetter(long number) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(number);
    }
}
