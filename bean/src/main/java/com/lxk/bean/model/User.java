package com.lxk.bean.model;

import lombok.Data;

/**
 * 用户model
 * <p>
 * @author lxk on 2017/8/31
 */
@Data
public class User {
    private String name;
    private String pwd;


    public User() {
    }

    public User(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
