package com.wix.mediaplatform.dto.response;

import com.wix.mediaplatform.exception.ExceptionFactory;
import com.wix.mediaplatform.exception.MediaPlatformException;

public class RestResponse<T> {

    private int code;

    private String message;

    private T payload;

    public int getCode() {
        return code;
    }

    public void throwForErrorCode() throws MediaPlatformException {
        MediaPlatformException exception = ExceptionFactory.CreateException(this);
        if (exception != null) {
            throw exception;
        }
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
