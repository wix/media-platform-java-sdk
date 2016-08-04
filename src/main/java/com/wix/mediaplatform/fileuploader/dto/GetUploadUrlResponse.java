package com.wix.mediaplatform.fileuploader.dto;

public class GetUploadUrlResponse {

    private String uploadUrl;

    private String uploadToken;

    public GetUploadUrlResponse() {
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public String getUploadToken() {
        return uploadToken;
    }
}
