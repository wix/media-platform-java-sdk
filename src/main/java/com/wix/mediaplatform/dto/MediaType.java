package com.wix.mediaplatform.dto;

import com.google.gson.annotations.SerializedName;

public enum MediaType {

    @SerializedName("music")
    AUDIO ("music"),
    @SerializedName("video")
    VIDEO ("video"),
    @SerializedName("picture")
    IMAGE ("picture"),
    @SerializedName("document")
    DOCUMENT ("document");

    private String mediaType;

    MediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaType() {
        return mediaType;
    }
}
