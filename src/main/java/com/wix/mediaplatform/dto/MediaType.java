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
    DOCUMENT ("document"),
    @SerializedName("secure_music")
    SECURE_AUDIO ("secure_music"),
    @SerializedName("secure_video")
    SECURE_VIDEO ("secure_video"),
    @SerializedName("secure_picture")
    SECURE_IMAGE ("secure_picture"),
    @SerializedName("secure_document")
    SECURE_DOCUMENT ("secure_document");

    private String mediaType;

    MediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaType() {
        return mediaType;
    }
}
