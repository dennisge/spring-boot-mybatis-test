/*
 * Copyright © 2019 Weizhen. All rights reserved.
 */
package com.lankio.domain.common;

/**
 * //TODO comments
 *
 * @author: DENNIS
 **/
public enum ResultCode {

    OK(200, "OK"),

    BAD_REQUEST(400, "Bad Request"),

    NOT_FOUND(404, "Not Found"),

    CONFLICT_DUPLICATE_KEY(405, "Duplicate Conflict"),

    PARAM_ERROR(444, "Parameter Error");

    private int code;

    private String msg;

    ResultCode(int code, String msg) {

        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
