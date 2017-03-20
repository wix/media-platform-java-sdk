package com.wix.mediaplatform.dto.response;

import com.wix.mediaplatform.dto.metadata.FileDescriptor;

public class ListFilesResponse {

    private String nextPageToken;

    private FileDescriptor[] files;

    public ListFilesResponse() {
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public FileDescriptor[] getFiles() {
        return files;
    }
}
