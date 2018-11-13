package com.wix.mediaplatform.v6.service.file;

import com.wix.mediaplatform.v6.dto.metadata.FileMetadata;
import com.wix.mediaplatform.v6.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v6.service.MediaPlatformRequest;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class FileMetadataRequest extends MediaPlatformRequest<FileMetadata> {

    private String path;

    FileMetadataRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl) {
        super(authenticatedHTTPClient, "GET", baseUrl + "/files/metadata");
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

    public FileMetadataRequest setPath(String path) {
        this.path = path;
        return this;
    }
}
