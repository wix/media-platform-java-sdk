package com.wix.mediaplatform.dto.folder;

import com.google.gson.annotations.SerializedName;
import com.wix.mediaplatform.dto.MediaType;

import java.util.Set;

public class FolderDTO {

    @SerializedName("folder_id")
    private String folderId;

    @SerializedName("parent_folder_id")
    private String parentFolderId;

    @SerializedName("folder_name")
    private String folderName;

    @SerializedName("media_type")
    private MediaType mediaType;

    private Set<String> tags;

    @SerializedName("created_ts")
    private long dateCreated;

    @SerializedName("modified_ts")
    private long dateUpdated;

    public FolderDTO(String folderId, String parentFolderId, String folderName, MediaType mediaType, Set<String> tags, long dateCreated, long dateUpdated) {
        this.folderId = folderId;
        this.parentFolderId = parentFolderId;
        this.folderName = folderName;
        this.mediaType = mediaType;
        this.tags = tags;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    public String getFolderId() {
        return folderId;
    }

    public String getParentFolderId() {
        return parentFolderId;
    }

    public String getFolderName() {
        return folderName;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public Set<String> getTags() {
        return tags;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public long getDateUpdated() {
        return dateUpdated;
    }
}
