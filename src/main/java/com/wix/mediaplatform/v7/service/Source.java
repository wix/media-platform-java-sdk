package com.wix.mediaplatform.v7.service;

public class Source {

    private String fileId;

    private String path;

    public Source() {
    }

    public Source setFileId(String fileId) {
        this.fileId = fileId;
        return this;
    }

    public Source setPath(String path) {
        this.path = path;
        return this;
    }

    public String getFileId() {
        return fileId;
    }

    public String getPath() {
        return path;
    }
}
