package com.lxk.design.pattern.compositeentity;

/**
 * @author LiXuekai on 2020/7/26
 */
public class Client {
    private CompositeEntity compositeEntity = new CompositeEntity();

    public void printData() {
        String[] data = compositeEntity.getData();
        for (String datum : data) {
            System.out.println("Data: " + datum);
        }
    }

    public void setData(String data1, String data2) {
        compositeEntity.setData(data1, data2);
    }
}
