package com.wix.mediaplatform.dto.management;

import com.google.gson.annotations.SerializedName;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

public class UpdateFileRequest {

    @SerializedName("original_file_name")
    private String originalFileName;

    @SerializedName("parent_folder_id")
    private String parentFolderId;

    private Set<String> tags = newHashSet();

    public UpdateFileRequest() {
    }

    public UpdateFileRequest(String originalFileName, String parentFolderId, Set<String> tags) {
        this.originalFileName = originalFileName;
        this.parentFolderId = parentFolderId;
        this.tags = tags;
    }

    public UpdateFileRequest setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
        return this;
    }

    public UpdateFileRequest setParentFolderId(String parentFolderId) {
        this.parentFolderId = parentFolderId;
        return this;
    }

    public UpdateFileRequest setTags(Set<String> tags) {
        this.tags = tags;
        return this;
    }

    public UpdateFileRequest addTag(String tag) {
        this.tags.add(tag);
        return this;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public String getParentFolderId() {
        return parentFolderId;
    }

    public Set<String> getTags() {
        return tags;
    }
}
