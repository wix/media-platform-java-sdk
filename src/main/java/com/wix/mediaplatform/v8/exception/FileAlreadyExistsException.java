package com.wix.mediaplatform.v8.exception;

public class FileAlreadyExistsException extends MediaPlatformException {
    public FileAlreadyExistsException() {
        super("File already exists", 409);
    }
}
