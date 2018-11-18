package com.wix.mediaplatform.v6.service.file;

import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.MediaPlatformRequest;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class DeleteFileRequest extends MediaPlatformRequest {

    private String path;

    DeleteFileRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl) {
        super(authenticatedHTTPClient, "DELETE", baseUrl + "/files");
    }

    @Override
    protected Map<String, String> params() {
        Map<String, String> params = newHashMap();

        params.put("path", path);

        return params;
    }

    public String getPath() {
        return path;
    }

    public DeleteFileRequest setPath(String path) {
        this.path = path;
        return this;
    }
}
