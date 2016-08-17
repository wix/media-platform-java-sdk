package com.wix.mediaplatform.dto.upload;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

public class UploadRequest {

    private Set<String> tags = newHashSet();

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
