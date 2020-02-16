package com.wix.mediaplatform.v7.exception;

public class FileAlreadyExistsException extends MediaPlatformException {
    public FileAlreadyExistsException() {
        super("File already exists", 409);
    }
}
