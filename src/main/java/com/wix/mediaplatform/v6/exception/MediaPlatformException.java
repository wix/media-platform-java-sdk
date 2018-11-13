package com.wix.mediaplatform.v6.exception;


public class MediaPlatformException extends Exception {

    private int code;

    public MediaPlatformException() { super(); }

    public MediaPlatformException(String message) { super(message); }

    public MediaPlatformException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
