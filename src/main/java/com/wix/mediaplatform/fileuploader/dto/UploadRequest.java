package com.wix.mediaplatform.fileuploader.dto;

import java.util.Set;

public class UploadRequest {

    private Set<String> tags;

    private String parentFolderId;

    public UploadRequest() {
    }

    public UploadRequest(Set<String> tags, String parentFolderId) {
        this.tags = tags;
        this.parentFolderId = parentFolderId;
    }

    public Set<String> getTags() {
        return tags;
    }

    public String getParentFolderId() {
        return parentFolderId;
    }
}
