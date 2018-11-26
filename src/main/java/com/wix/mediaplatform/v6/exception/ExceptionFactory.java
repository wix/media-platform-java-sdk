package com.wix.mediaplatform.v6.exception;

import com.wix.mediaplatform.v6.service.RestResponse;
import org.jetbrains.annotations.Nullable;

// todo: fix
public final class ExceptionFactory {

    private ExceptionFactory() {}

    @Nullable
    public static MediaPlatformException createException(RestResponse restResponse) {

        int code = restResponse.getCode();

        if (code == 0) {
            return null;
        }

        ErrorCode errorCode = ErrorCode.find(code);
        if (errorCode == null) {
            return new MediaPlatformException(restResponse.getMessage(), restResponse.getCode());
        }

        switch (errorCode) {
            case ok:
                return null;
            case fileAlreadyExists:
                return new FileAlreadyExistsException();
            case notFound:
                return new ResourceNotFoundException(restResponse.getMessage());
            default:
                return new MediaPlatformException(restResponse.getMessage(), restResponse.getCode());
        }
    }
}
