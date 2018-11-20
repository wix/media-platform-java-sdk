package com.wix.mediaplatform.v6.exception;

public class ForbiddenException extends MediaPlatformException {
    public ForbiddenException() {
        super("Forbidden", 403);
    }
}
