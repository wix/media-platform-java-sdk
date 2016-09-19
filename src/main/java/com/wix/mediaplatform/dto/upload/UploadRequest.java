package com.wix.mediaplatform.dto.upload;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

public class UploadRequest {

    private Set<String> tags = newHashSet();

    @SerializedName("parent_folder_id")
    private String parentFolderId;

    public UploadRequest() {
    }

    public UploadRequest(Set<String> tags, @Nullable String parentFolderId) {
        this.tags = tags;
        this.parentFolderId = parentFolderId;
    }

    public UploadRequest setTags(Set<String> tags) {
        this.tags = tags;
        return this;
    }

    public UploadRequest addTag(String tag) {
        this.tags.add(tag);
        return this;
    }

    public UploadRequest setParentFolderId(String parentFolderId) {
        this.parentFolderId = parentFolderId;
        return this;
    }

    public Set<String> getTags() {
        return tags;
    }

    @Nullable
    public String getParentFolderId() {
        return parentFolderId;
    }
}
