package com.wix.mediaplatform.v6.service.file;

import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.FileDescriptor;
import com.wix.mediaplatform.v6.service.MediaPlatformRequest;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class FileRequest extends MediaPlatformRequest<FileDescriptor> {

    private String path;

    public FileRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl) {
        super(authenticatedHTTPClient, "GET", baseUrl + "/files", FileDescriptor.class);
    }

    public String getPath() {
        return path;
    }

    public FileRequest setPath(String path) {
        this.path = path;
        return this;
    }

    @Override
    protected Map<String, String> params() {
        Map<String, String> params = newHashMap();
        params.put("path", path);

        return params;
    }
}
