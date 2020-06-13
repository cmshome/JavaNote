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
     *
     * 命令：jmap -heap 757471                                                                                                                                                                                            1 ↵
     * Attaching to process ID 757471, please wait...
     * Debugger attached successfully.
     * Server compiler detected.
     * JVM version is 25.131-b11
     *
     * using parallel threads in the new generation.
     * using thread-local object allocation.
     * Concurrent Mark-Sweep GC
     *
     * Heap Configuration:
     *    MinHeapFreeRatio         = 40
     *    MaxHeapFreeRatio         = 70
     *    MaxHeapSize              = 4294967296 (4096.0MB)
     *    NewSize                  = 697892864 (665.5625MB)
     *    MaxNewSize               = 697892864 (665.5625MB)
     *    OldSize                  = 3597074432 (3430.4375MB)
     *    NewRatio                 = 2
     *    SurvivorRatio            = 8
     *    MetaspaceSize            = 21807104 (20.796875MB)
     *    CompressedClassSpaceSize = 1073741824 (1024.0MB)
     *    MaxMetaspaceSize         = 17592186044415 MB
     *    G1HeapRegionSize         = 0 (0.0MB)
     *
     * Heap Usage:
     * New Generation (Eden + 1 Survivor Space):
     *    capacity = 628162560 (599.0625MB)
     *    used     = 65199104 (62.1787109375MB)
     *    free     = 562963456 (536.8837890625MB)
     *    10.379336202399582% used
     * Eden Space:
     *    capacity = 558432256 (532.5625MB)
     *    used     = 59825312 (57.053863525390625MB)
     *    free     = 498606944 (475.5086364746094MB)
     *    10.713083164021242% used
     * From Space:
     *    capacity = 69730304 (66.5MB)
     *    used     = 5373792 (5.124847412109375MB)
     *    free     = 64356512 (61.375152587890625MB)
     *    7.706537461818609% used
     * To Space:
     *    capacity = 69730304 (66.5MB)
     *    used     = 0 (0.0MB)
     *    free     = 69730304 (66.5MB)
     *    0.0% used
     * concurrent mark-sweep generation:
     *    capacity = 3597074432 (3430.4375MB)
     *    used     = 2498293632 (2382.5584716796875MB)
     *    free     = 1098780800 (1047.8790283203125MB)
     *    69.45348724994079% used
     *
     * 22692 interned Strings occupying 3077000 bytes.
     *
     */
    public static void main(String[] args) {

        jmap();

        jinfo();

        jstat();
    }

    /**
     * jmap -heap 出来的jvm参数，都自带单位，好清晰。
     */
    private static void jmap() {
        long newGeneration = 628162560L,
                newGenerationUsed = 65199104,
                newGenerationFree = 562963456,
                eden = 558432256,
                edenUsed = 59825312,
                edenFree = 498606944,
                fromCapacity = 69730304,
                fromUsed = 5373792,
                fromFree = 64356512,
                cmsGeneration = 3597074432L,
                cmsUsed = 2498293632L,
                cmsFree = 1098780800
                ;

        long newGener = newGenerationUsed + newGenerationFree;
        System.out.println("New Generation = Eden + 1 Survivor Space)  is " + (newGener == newGeneration));
        long edenTotal = edenFree + edenUsed;
        System.out.println(edenTotal == eden);


        long sAndO = cmsGeneration + newGeneration + fromCapacity;
        // 这个加出来就是 初始化时候堆的大小。 2个相等的。
        System.out.println(showNumberBetter(sAndO));


    }

    /**
     * jstat 出来的jvm参数 下面的这些个的但是 k bytes KB
     */
    private static void jstat() {
        long s0 = 68096L,
                s1 = 68096L,
                eden = 545344L,
                old = 3512768L,
                mc = 71216,
                ccs = 9304;
        long young = s0 + s1 + eden;
        System.out.println("s0 + s1 + eden = " + showNumberBetter(young));

        long newCapacity = s0 + eden;
        System.out.println("new size is " +showNumberBetter(newCapacity));

        long heap = young + old;
        System.out.println("young + old = " + showNumberBetter(heap));

        long total = heap + mc + ccs;
        System.out.println("heap + mc + ccs = " + showNumberBetter(total));
    }

    /**
     * jinfo 出来的jvm参数，这三个的单位是 byte 字节
     */
    private static void jinfo() {
        long maxHeapSize = 4294967296L,
                newSize = 697892864L,
                oldSize = 3597074432L;

        System.out.println("InitialHeapSize = MaxHeapSize = " + showNumberBetter(maxHeapSize));

        long all = newSize + oldSize;
        System.out.println("newSize + oldSize = " + showNumberBetter(all));

        System.out.println("newSize + oldSize == maxHeapSize is true.");
        System.out.println("NewSize is " + showNumberBetter(newSize));
        System.out.println("OldSize is " + showNumberBetter(oldSize));
    }

    private static String showNumberBetter(long number) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(number);
    }
}
