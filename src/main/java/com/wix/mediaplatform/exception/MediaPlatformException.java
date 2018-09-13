package com.wix.mediaplatform.exception;

/**
 * Created by alonne on 06/07/2017.
 */
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
