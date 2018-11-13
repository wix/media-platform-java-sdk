package com.wix.mediaplatform.v6.exception;

import com.wix.mediaplatform.v6.service.RestResponse;

/**
 * Created by alonne on 06/07/2017.
 */
public final class ExceptionFactory {

    private ExceptionFactory() {}

    public static MediaPlatformException createException(RestResponse restResponse) {
        int code = restResponse.getCode();
        ErrorCode errorCode = ErrorCode.find(code);
        if (errorCode == null) {
            return new MediaPlatformException(String.format("Error %d", code), code);
        }

        switch (errorCode) {
            case ok:
                return null;
            case fileAlreadyExists:
                return new FileAlreadyExistsException();
            case notFound:
                return new ResourceNotFoundException(restResponse.getMessage());
            default:
                return new MediaPlatformException(String.format("Error %d", code), code);
        }
    }
}
