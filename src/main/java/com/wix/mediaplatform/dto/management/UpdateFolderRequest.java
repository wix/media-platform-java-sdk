package com.wix.mediaplatform.dto.management;

import com.google.gson.annotations.SerializedName;

public class UpdateFolderRequest {

    @SerializedName("folder_name")
    private String folderName;

    public UpdateFolderRequest() {
    }

    public UpdateFolderRequest(String folderName) {
        this.folderName = folderName;
    }

    public String getFolderName() {
        return folderName;
    }

    public UpdateFolderRequest setFolderName(String folderName) {
        this.folderName = folderName;
        return this;
    }
}
