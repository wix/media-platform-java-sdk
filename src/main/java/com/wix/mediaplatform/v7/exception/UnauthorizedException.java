package com.wix.mediaplatform.v7.exception;

public class UnauthorizedException extends MediaPlatformException {
    public UnauthorizedException() {
        super("Unauthorized", 401);
    }

}
