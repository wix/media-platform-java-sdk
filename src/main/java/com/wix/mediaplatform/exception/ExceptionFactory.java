package com.wix.mediaplatform.exception;

import com.wix.mediaplatform.dto.response.RestResponse;

/**
 * Created by alonne on 06/07/2017.
 */
public final class ExceptionFactory {
    private ExceptionFactory() {}
    public static MediaPlatformException CreateException(RestResponse restResponse) {
        int code = restResponse.getCode();
        if (code >= ErrorCode.values().length) {
            return new MediaPlatformException(String.format("Error %d", code));
        }
        ErrorCode errorCode = ErrorCode.values()[code];

        switch (errorCode) {
            case ok:
                return null;
            case fileAlreadyExists:
                return new FileAlreadyExistsException();
            default:
                return new MediaPlatformException(String.format("Error %d", code));
        }

    }
}
