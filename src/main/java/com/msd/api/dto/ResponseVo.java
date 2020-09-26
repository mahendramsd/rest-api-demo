package com.msd.api.dto;

import java.io.Serializable;

public class ResponseVo implements Serializable {

    private final String token;

    public ResponseVo(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

}
