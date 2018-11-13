package com.wix.mediaplatform.v6.exception;

public class ResourceNotFoundException extends MediaPlatformException {

    public ResourceNotFoundException(String message) {
        super(message, 404);
    }
}
