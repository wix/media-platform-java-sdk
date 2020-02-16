package com.wix.mediaplatform.v7.exception;

public class ForbiddenException extends MediaPlatformException {
    public ForbiddenException() {
        super("Forbidden", 403);
    }
}
