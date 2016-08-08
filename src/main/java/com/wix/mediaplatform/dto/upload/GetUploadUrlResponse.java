package com.wix.mediaplatform.dto.upload;

import com.google.gson.annotations.SerializedName;

public class GetUploadUrlResponse {

    @SerializedName("upload_url")
    private String uploadUrl;

    @SerializedName("upload_token")
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
