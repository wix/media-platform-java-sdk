package com.wix.mediaplatform.dto.folder;

import com.google.gson.annotations.SerializedName;

public class FolderDTO {

    @SerializedName("folder_id")
    private String folderId;

    @SerializedName("parent_folder_id")
    private String parentFolderId;

    @SerializedName("folder_name")
    private String folderName;

    @SerializedName("media_type")
    private String mediaType;

    @SerializedName("created_ts")
    private long dateCreated;

    @SerializedName("modified_ts")
    private long dateUpdated;

    public FolderDTO() {
    }

    public FolderDTO(String folderId, String parentFolderId, String folderName, String mediaType, long dateCreated, long dateUpdated) {
        this.folderId = folderId;
        this.parentFolderId = parentFolderId;
        this.folderName = folderName;
        this.mediaType = mediaType;
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

    public String getMediaType() {
        return mediaType;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public long getDateUpdated() {
        return dateUpdated;
    }

    @Override
    public String toString() {
        return "FolderDTO{" +
                "folderId='" + folderId + '\'' +
                ", parentFolderId='" + parentFolderId + '\'' +
                ", folderName='" + folderName + '\'' +
                ", mediaType=" + mediaType +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                '}';
    }
}
