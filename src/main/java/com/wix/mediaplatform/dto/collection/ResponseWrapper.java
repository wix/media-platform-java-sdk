package com.wix.mediaplatform.dto.collection;

import com.google.gson.annotations.SerializedName;

public class ResponseWrapper<T> {

    private String message;

    private int code;

    private boolean error;

    @SerializedName(value="payload", alternate={"collection", "collections"})
    private T payload;

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public boolean isError() {
        return error;
    }

    public T getPayload() {
        return payload;
    }
}
