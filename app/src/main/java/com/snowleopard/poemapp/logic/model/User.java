package com.snowleopard.poemapp.logic.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 此类用于登录注册
 */
public class User implements Serializable {

    @SerializedName("password")
    private String password;

    @SerializedName("username")
    private String username;

    public User(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
