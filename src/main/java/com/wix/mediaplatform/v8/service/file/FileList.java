package com.wix.mediaplatform.v8.service.file;

import com.wix.mediaplatform.v8.service.FileDescriptor;

public class FileList {

    private String nextPageToken;

    private FileDescriptor[] files;

    public FileList() {
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public FileDescriptor[] getFiles() {
        return files;
    }
}
