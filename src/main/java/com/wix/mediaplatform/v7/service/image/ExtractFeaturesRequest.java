package com.wix.mediaplatform.v7.service.image;

import com.wix.mediaplatform.v7.http.AuthenticatedHTTPClient;
import com.wix.mediaplatform.v7.metadata.features.ImageFeatures;
import com.wix.mediaplatform.v7.service.MediaPlatformRequest;

import java.util.HashMap;
import java.util.Map;

public class ExtractFeaturesRequest extends MediaPlatformRequest<ImageFeatures> {

    private String path;

    private String fileId;

    private String[] features;

    ExtractFeaturesRequest(AuthenticatedHTTPClient authenticatedHTTPClient, String baseUrl) {
        super(authenticatedHTTPClient, "GET", baseUrl + "/images/features", ImageFeatures.class);
    }

    public String getPath() {
        return path;
    }

    public ExtractFeaturesRequest setPath(String path) {
        this.path = path;
        return this;
    }

    public String getFileId() {
        return fileId;
    }

    public ExtractFeaturesRequest setFileId(String fileId) {
        this.fileId = fileId;
        return this;
    }

    public String[] getFeatures() {
        return features;
    }

    public ExtractFeaturesRequest setFeatures(String[] features) {
        this.features = features;
        return this;
    }

    @Override
    protected Map<String, String> params() {
        Map<String, String> params = new HashMap<>();

        params.put("features", String.join(",", features));

        if (path != null) {
            params.put("path", path);
        }

        if (fileId != null) {
            params.put("fileId", fileId);
        }

        return params;
    }
}
