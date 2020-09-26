package com.msd.api.dto;

import java.io.Serializable;

public class RequestVo implements Serializable {

    private String username;
    private String password;

    public RequestVo() {}

    public RequestVo(String username, String password) {

        this.setUsername(username);

        this.setPassword(password);

    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;

    }

}