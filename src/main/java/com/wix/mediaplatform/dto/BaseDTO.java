package com.wix.mediaplatform.dto;

import com.google.gson.annotations.SerializedName;

import java.util.Set;

public abstract class BaseDTO {

    @SerializedName("parent_folder_id")
    private String parentFolderId;

    private String hash;

    @SerializedName("original_file_name")
    private String originalFileName;

    @SerializedName("file_name")
    private String fileName;

    @SerializedName("file_url")
    private String fileUrl;

    @SerializedName("file_size")
    private long fileSize;

    @SerializedName("icon_url")
    private String iconUrl;

    @SerializedName("media_type")
    private String mediaType;

    @SerializedName("mime_type")
    private String mimeType;

    private Set<String> lables;

    private Set<String> tags;

    @SerializedName("created_ts")
    private long dateCreated;

    @SerializedName("modified_ts")
    private long dateModified;

    public BaseDTO() {
    }

    public String getParentFolderId() {
        return parentFolderId;
    }

    public String getHash() {
        return hash;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getMimeType() {
        return mimeType;
    }

    public Set<String> getLables() {
        return lables;
    }

    public Set<String> getTags() {
        return tags;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public long getDateModified() {
        return dateModified;
    }
}


