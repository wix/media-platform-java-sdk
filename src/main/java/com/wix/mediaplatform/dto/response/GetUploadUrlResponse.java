package com.wix.mediaplatform.dto.response;

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
