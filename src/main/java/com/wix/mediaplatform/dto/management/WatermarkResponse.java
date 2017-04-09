package com.wix.mediaplatform.dto.management;

import com.google.gson.annotations.SerializedName;
import com.wix.mediaplatform.dto.management.watermark.Watermark;
import com.wix.mediaplatform.dto.management.watermark.WatermarkedImage;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

public class WatermarkResponse extends Watermark<WatermarkResponse> {

    @SerializedName("files")
    private Set<WatermarkedImage> watermarkedImages = newHashSet();

    public Set<WatermarkedImage> getWatermarkedImages() {
        return watermarkedImages;
    }
}
