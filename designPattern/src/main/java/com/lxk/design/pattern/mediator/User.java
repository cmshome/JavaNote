package com.lxk.design.pattern.mediator;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiXuekai on 2020/7/24
 */
@Data
@NoArgsConstructor
public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public void sendMessage(String message) {
        ChatRoom.showMessage(this, message);
    }
}
