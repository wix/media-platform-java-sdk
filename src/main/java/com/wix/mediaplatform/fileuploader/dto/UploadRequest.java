package com.wix.mediaplatform.fileuploader.dto;

import com.sun.org.apache.xpath.internal.operations.String;

import java.util.Set;

public class UploadRequest {

    private String fileName;

    private Set<String> tags;

    private String parentFolderId;

    public UploadRequest() {
    }

    public UploadRequest(String fileName, Set<String> tags, String parentFolderId) {

        this.fileName = fileName;

        this.tags = tags;

        this.parentFolderId = parentFolderId;
    }

    public String getFileName() {
        return fileName;
    }

    public Set<String> getTags() {
        return tags;
    }

    public String getParentFolderId() {
        return parentFolderId;
    }
}
