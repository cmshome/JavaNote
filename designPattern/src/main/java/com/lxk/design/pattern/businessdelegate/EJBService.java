package com.lxk.design.pattern.businessdelegate;

/**
 * @author LiXuekai on 2020/7/26
 */
public class EJBService implements BusinessService {

    @Override
    public void doProcessing() {
        System.out.println("Processing task by invoking EJB Service");
    }
}
