package com.wix.mediaplatform.dto.download;

import com.google.gson.annotations.SerializedName;

public class GetSecureUrlResponse {

    @SerializedName("reference_id")
    private String referenceId;

    private String encoding;

    @SerializedName("url_path")
    private String path;

    public GetSecureUrlResponse() {
    }

    public String getReferenceId() {
        return referenceId;
    }

    public String getEncoding() {
        return encoding;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "GetSecureUrlResponse{" +
                "referenceId='" + referenceId + '\'' +
                ", encoding='" + encoding + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
