package com.wix.mediaplatform.exception;

public class UnauthorizedException extends MediaPlatformException {
    public UnauthorizedException() {
        super("Unauthorized", 401);
    }

}
