package com.snowleopard.poemapp.logic.model;

/**
 * 此类用于查看用户信息
 */
public class UserInfo {
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
