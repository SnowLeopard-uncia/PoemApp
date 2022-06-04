package com.snowleopard.poemapp.logic.model;

import com.google.gson.annotations.SerializedName;

/**
 * 此类用于查看用户信息
 */
public class UserInfo {
    @SerializedName("pro_path") //这是一个bug的来源 忘记加序列化的名字了
    private String proPath;

    private String username;

    public UserInfo(String proPath, String username) {
        this.proPath = proPath;
        this.username = username;
    }

    public String getProPath() {
        return proPath;
    }

    public void setProPath(String proPath) {
        this.proPath = proPath;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
