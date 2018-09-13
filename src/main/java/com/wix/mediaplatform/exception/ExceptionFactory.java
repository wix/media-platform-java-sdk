package com.wix.mediaplatform.exception;

import com.wix.mediaplatform.dto.response.RestResponse;

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
