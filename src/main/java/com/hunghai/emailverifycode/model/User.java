package com.hunghai.emailverifycode.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String name;
    private String age;
    private String phone;

    public User(String name, String age, String phone) {
        this.name = name;
        this.age = age;
        this.phone = phone;
    }
}
