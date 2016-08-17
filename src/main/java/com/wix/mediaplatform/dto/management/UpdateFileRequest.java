package com.wix.mediaplatform.dto.management;

import com.google.gson.annotations.SerializedName;

import java.util.Set;

public class UpdateFileRequest {

    @SerializedName("original_file_name")
    private String originalFileName;

    @SerializedName("parent_folder_id")
    private String parentFolderId;

    private Set<String> tags;

    public UpdateFileRequest(String originalFileName, String parentFolderId, Set<String> tags) {
        this.originalFileName = originalFileName;
        this.parentFolderId = parentFolderId;
        this.tags = tags;
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
