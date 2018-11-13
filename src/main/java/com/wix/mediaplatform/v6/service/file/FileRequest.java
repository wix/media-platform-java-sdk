package com.wix.mediaplatform.v6.service.file;

import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.FileDescriptor;
import com.wix.mediaplatform.v6.service.MediaPlatformRequest;

public class FileRequest extends MediaPlatformRequest<FileDescriptor> {

    private String path;

    FileRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl) {
        super(authenticatedHTTPClient, "GET", baseUrl + "/files");
    }

    public String getPath() {
        return path;
    }

    public FileRequest setPath(String path) {
        this.path = path;
        return this;
    }
}
