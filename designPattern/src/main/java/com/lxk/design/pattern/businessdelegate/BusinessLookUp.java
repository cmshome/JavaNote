package com.lxk.design.pattern.businessdelegate;

/**
 * @author LiXuekai on 2020/7/26
 */
public class BusinessLookUp {
    public BusinessService getBusinessService(String serviceType) {
        if (serviceType.equalsIgnoreCase("EJB")) {
            return new EJBService();
        } else {
            return new JMSService();
        }
    }
}
