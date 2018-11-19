package com.wix.mediaplatform.v6.service;

import com.wix.mediaplatform.v6.exception.ExceptionFactory;
import com.wix.mediaplatform.v6.exception.MediaPlatformException;
import org.jetbrains.annotations.Nullable;

public class RestResponse<P> {

    private int code;

    private String message;

    private P payload;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Nullable
    public P getPayload() {
        return payload;
    }

    public void throwForErrorCode() throws MediaPlatformException {
        MediaPlatformException exception = ExceptionFactory.createException(this);
        if (exception != null) {
            throw exception;
        }
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
