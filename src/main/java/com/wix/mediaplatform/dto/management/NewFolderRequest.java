package com.wix.mediaplatform.dto.management;

import com.google.gson.annotations.SerializedName;
import com.wix.mediaplatform.dto.MediaType;

public class NewFolderRequest {

    @SerializedName("media_type")
    private MediaType mediaType;

    @SerializedName("folder_name")
    private String folderName;

    @SerializedName("parent_folder_id")
    private String parentFolderId;

    public NewFolderRequest(MediaType mediaType, String folderName, String parentFolderId) {
        this.mediaType = mediaType;
        this.folderName = folderName;
        this.parentFolderId = parentFolderId;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public String getFolderName() {
        return folderName;
    }

    public String getParentFolderId() {
        return parentFolderId;
    }
}
