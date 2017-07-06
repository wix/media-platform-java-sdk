package com.wix.mediaplatform.exception;

/**
 * Created by alonne on 06/07/2017.
 */
public enum ErrorCode {
    ok(0),
    fileAlreadyExists(1);

    private int value;

    ErrorCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
