package com.example.OBJECT;

import java.io.Serializable;

public class User implements Serializable {
    public String username;
    public String password;
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
