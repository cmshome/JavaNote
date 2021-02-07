package com.lxk.jdk.jvm.memory.model.running;

import com.alibaba.fastjson.annotation.JSONField;
import com.lxk.jdk.jvm.memory.inteface.OutOfMemory;
import com.lxk.jdk.jvm.memory.inteface.Shared;
import lombok.Data;

/**
 * Java 堆 内存
 *
 * @author LiXuekai on 2019/12/31
 */
@Data
public class Heap implements OutOfMemory, Shared {

    /**
     * 年轻代
     */
    @JSONField(ordinal = 0)
    private Young young;

    /**
     * 老年代
     */
    @JSONField(ordinal = 1)
    private Old old;


    public Heap() {
        this.young = new Young();
        this.old = new Old();
    }


    /**
     * 年轻代
     */
    @Data
    class Young {
        /**
         * E区
         */
        @JSONField(ordinal = 0)
        private Eden eden;

        /**
         * S0区
         */
        @JSONField(ordinal = 1)
        private Survivor s0;

        /**
         * S1区
         */
        @JSONField(ordinal = 2)
        private Survivor s1;


        public Young() {
            this.eden = new Eden();
            this.s0 = new Survivor();
            this.s1 = new Survivor();
        }


        /**
         * E区
         */
        @Data
        class Eden {

        }

        /**
         * S区
         */
        @Data
        class Survivor {
        }
    }


    /**
     * 老年代
     */
    @Data
    class Old {
        private ConstantPool constantPool;

        public Old() {
            this.constantPool = new ConstantPool();
        }


        @Data
        class ConstantPool {
            private String part0 = "jdk1.7的时候，常量池已经被安排在堆";
        }
    }

}
