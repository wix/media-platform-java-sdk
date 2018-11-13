package com.wix.mediaplatform.v6.exception;

public class FileAlreadyExistsException extends MediaPlatformException {
    public FileAlreadyExistsException() {
        super("File already exists", 409);
    }
}
