package com.wix.mediaplatform.dto;

public class ResponseWrapper<T> {

    private int code;

    private String message;

    private T payload;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getPayload() {
        return payload;
    }
}
