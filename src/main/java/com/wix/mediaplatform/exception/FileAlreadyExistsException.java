package com.wix.mediaplatform.exception;

public class FileAlreadyExistsException extends MediaPlatformException {
    public FileAlreadyExistsException() {
        super("File already exists", 409);
    }
}
