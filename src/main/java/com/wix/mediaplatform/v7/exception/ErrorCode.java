package com.wix.mediaplatform.v7.exception;

import org.jetbrains.annotations.Nullable;

/**
 * Created by alonne on 06/07/2017.
 */
public enum ErrorCode {
    ok(0),
    fileAlreadyExists(1),
    notFound(404);

    private final int value;

    ErrorCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Nullable
    public static ErrorCode find(int code) {

        for (ErrorCode c : ErrorCode.values()) {
            if (c.value == code) {
                return c;
            }
        }

        return null;
    }
}
