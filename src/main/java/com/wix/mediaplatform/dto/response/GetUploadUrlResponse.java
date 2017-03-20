package com.wix.mediaplatform.dto.response;

public class GetUploadUrlResponse {

    private String uploadUrl;

    private String uploadToken;

    public GetUploadUrlResponse() {
    }

    public GetUploadUrlResponse(String uploadUrl, String uploadToken) {
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
