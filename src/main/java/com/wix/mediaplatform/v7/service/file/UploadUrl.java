package com.wix.mediaplatform.v7.service.file;

public class UploadUrl {

    private String uploadUrl;

    private String uploadToken;

    public UploadUrl() {
    }

    public UploadUrl(String uploadUrl, String uploadToken) {
        this.uploadUrl = uploadUrl;
        this.uploadToken = uploadToken;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public String getUploadToken() {
        return uploadToken;
    }
}
