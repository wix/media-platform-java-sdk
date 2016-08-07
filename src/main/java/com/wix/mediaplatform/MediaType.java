package com.wix.mediaplatform;

public enum MediaType {

    AUDIO ("music"),
    VIDEO ("video"),
    IMAGE ("picture"),
    DOCUMENT ("document");

    private String mediaType;

    MediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaType() {
        return mediaType;
    }
}
