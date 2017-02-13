package com.luseen.fragmentarg;


import java.io.Serializable;

/**
 * Created by Chatikyan on 12.02.2017.
 */

public class User implements Serializable {

    private String userName;
    private int phoneNumber;
    private int age;

    public User(String userName, int phoneNumber, int age) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public int getAge() {
        return age;
    }
}
