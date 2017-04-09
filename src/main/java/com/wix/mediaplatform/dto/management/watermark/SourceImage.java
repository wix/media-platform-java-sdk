package com.wix.mediaplatform.dto.management.watermark;

import com.google.gson.annotations.SerializedName;

public class SourceImage extends Watermark<SourceImage> {

    @SerializedName("file_name")
    private String fileName;

    public SourceImage() {
    }

    public SourceImage setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getFileName() {
        return fileName;
    }
}
