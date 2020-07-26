package com.lxk.design.pattern.businessdelegate;

/**
 * @author LiXuekai on 2020/7/26
 */
public class Client {

    private BusinessDelegate businessService;

    public Client(BusinessDelegate businessService) {
        this.businessService = businessService;
    }

    public void doTask() {
        businessService.doTask();
    }
}
