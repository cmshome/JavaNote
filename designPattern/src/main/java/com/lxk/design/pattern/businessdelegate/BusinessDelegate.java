package com.lxk.design.pattern.businessdelegate;

/**
 * @author LiXuekai on 2020/7/26
 */
public class BusinessDelegate {
    private BusinessLookUp lookupService = new BusinessLookUp();
    private String serviceType;

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public void doTask() {
        BusinessService businessService = lookupService.getBusinessService(serviceType);
        businessService.doProcessing();
    }
}
