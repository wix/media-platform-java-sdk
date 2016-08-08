package com.wix.mediaplatform.dto;

import java.util.Set;

public abstract class BaseDTO {

    private String parentFolderId;

    private String hash;

    private String originalFileName;

    private String fileName;

    private String fileUrl;

    private long fileSize;

    private String mediaType;

    private String mimeType;

    private Set<String> lables;

    private Set<String> tags;

    private long dateCreated;

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


