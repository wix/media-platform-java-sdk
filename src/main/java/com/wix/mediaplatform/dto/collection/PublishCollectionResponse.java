package com.wix.mediaplatform.dto.collection;

import com.google.gson.annotations.SerializedName;

public class PublishCollectionResponse {

    @SerializedName("published_file_path")
    private String filePath;

    public PublishCollectionResponse() {
    }

    public String getFilePath() {
        return filePath;
    }
}
