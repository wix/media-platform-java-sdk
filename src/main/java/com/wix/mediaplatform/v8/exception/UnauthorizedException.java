package com.wix.mediaplatform.v8.exception;

public class UnauthorizedException extends MediaPlatformException {
    public UnauthorizedException() {
        super("Unauthorized", 401);
    }
}
