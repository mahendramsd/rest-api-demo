package com.msd.api.util;

import org.springframework.http.HttpStatus;

public enum CustomErrorCodes {
    FAIL_USER(1000, "User No Found",
            HttpStatus.BAD_REQUEST), USER_EXISTS(1001, " User already exists",
            HttpStatus.BAD_REQUEST), INVALID_PASSWORD(1002, "Invalid Password",
            HttpStatus.BAD_REQUEST), INVALID_USERNAME(1003, "Invalid email / username",
            HttpStatus.BAD_REQUEST), PARENT_NOT_FOUND(1004, "No parent record exist for given id",
            HttpStatus.NOT_FOUND );

    private final int id;
    private final String msg;
    private final HttpStatus httpCode;

    CustomErrorCodes(int id, String msg, HttpStatus httpCode) {
        this.id = id;
        this.msg = msg;
        this.httpCode = httpCode;
    }

    public int getId() {
        return this.id;
    }

    public String getMsg() {
        return this.msg;
    }

    public HttpStatus getHttpCode() {
        return this.httpCode;
    }

}
