package com.wix.mediaplatform.dto.response;

public class RestResponse<T> {

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

    @Override
    public String toString() {
        return "RestResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", payload=" + payload +
                '}';
    }
}
