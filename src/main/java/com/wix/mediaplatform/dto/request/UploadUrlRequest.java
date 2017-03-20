package com.wix.mediaplatform.dto.request;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class UploadUrlRequest {

    private String mimeType;

    private String path;

    public UploadUrlRequest() {
    }

    public UploadUrlRequest setMimeType(String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    public UploadUrlRequest setPath(String path) {
        this.path = path;
        return this;
    }

    public String getMimeType() {
        return mimeType;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> toParams() {
        Map<String, String> params = newHashMap();
        if (mimeType != null) {
            params.put("mimeType", mimeType);
        }
        if (path != null) {
            params.put("path", path);
        }

        return params;
    }
}
