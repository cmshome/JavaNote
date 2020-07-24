package com.lxk.design.pattern.mediator;

import java.util.Date;

/**
 * @author LiXuekai on 2020/7/24
 */
public class ChatRoom {
    public static void showMessage(User user, String message) {
        System.out.println(new Date().toString() + " [" + user.getName() + "] : " + message);
    }
}
