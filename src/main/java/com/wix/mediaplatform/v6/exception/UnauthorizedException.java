package com.wix.mediaplatform.v6.exception;

public class UnauthorizedException extends MediaPlatformException {
    public UnauthorizedException() {
        super("Unauthorized", 401);
    }

}
