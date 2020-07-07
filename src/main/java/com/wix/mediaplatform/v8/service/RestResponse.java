package com.wix.mediaplatform.v8.service;

import com.wix.mediaplatform.v8.exception.ExceptionFactory;
import com.wix.mediaplatform.v8.exception.MediaPlatformException;
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

    public RestResponse<P> throwOrReturn() throws MediaPlatformException {
        MediaPlatformException exception = ExceptionFactory.createException(this);
        if (exception != null) {
            throw exception;
        }

        return this;
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
