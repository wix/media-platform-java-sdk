package com.wix.mediaplatform.dto;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

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

    public BaseDTO(String parentFolderId, String hash, String originalFileName, String fileName, String fileUrl, long fileSize, String iconUrl, String mediaType, String mimeType, Set<String> lables, Set<String> tags, long dateCreated, long dateModified) {
        this.parentFolderId = parentFolderId;
        this.hash = hash;
        this.originalFileName = originalFileName;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.fileSize = fileSize;
        this.iconUrl = iconUrl;
        this.mediaType = mediaType;
        this.mimeType = mimeType;
        this.lables = lables;
        this.tags = tags;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
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

    @Nullable
    public String getBaseUrl() {
        if (fileUrl != null) {
            String[] parts = fileUrl.split("/");
            if (parts.length >= 2) {
                return parts[0] + "/" + parts[1];
            }
            else {
                return parts[0];
            }
        } else {
            return null;
        }
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


