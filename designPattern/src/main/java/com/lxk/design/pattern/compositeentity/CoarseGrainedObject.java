package com.lxk.design.pattern.compositeentity;

/**
 * 粗粒度对象（Coarse-Grained Object） - 该对象包含依赖对象。它有自己的生命周期，也能管理依赖对象的生命周期。
 *
 * @author LiXuekai on 2020/7/26
 */
public class CoarseGrainedObject {
    private DependentObject1 do1 = new DependentObject1();
    private DependentObject2 do2 = new DependentObject2();

    public void setData(String data1, String data2) {
        do1.setData(data1);
        do2.setData(data2);
    }

    public String[] getData() {
        return new String[]{do1.getData(), do2.getData()};
    }
}
