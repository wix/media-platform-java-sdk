package com.wix.mediaplatform.dto.request;

public class Attachment {

    private String filename;

    public Attachment() {
    }

    public String getFilename() {
        return filename;
    }

    public Attachment setFilename(String filename) {
        this.filename = filename;
        return this;
    }
}
