package com.wix.mediaplatform.dto.management.watermark;

import com.google.gson.annotations.SerializedName;

public class WatermarkedImage extends SourceImage {

    @SerializedName("wm_man")
    private String watermarked;

    public WatermarkedImage() {
    }

    public String getWatermarked() {
        return watermarked;
    }
}
