package com.wix.mediaplatform.v8.service.file;

public class UploadConfiguration {

    private String uploadUrl;

    public UploadConfiguration() {
    }

    public UploadConfiguration(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }
}