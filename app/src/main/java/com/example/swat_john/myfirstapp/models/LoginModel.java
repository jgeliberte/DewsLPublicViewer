package com.example.swat_john.myfirstapp.models;

import java.io.Serializable;

/**
 * Created by Swat-John on 3/24/2017.
 */

public class LoginModel implements Serializable {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String username = "";
    private String password = "";
}
