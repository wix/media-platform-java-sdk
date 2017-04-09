package com.wix.mediaplatform.dto.management;

import com.google.gson.annotations.SerializedName;
import com.wix.mediaplatform.dto.management.watermark.SourceImage;
import com.wix.mediaplatform.dto.management.watermark.Watermark;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

public class WatermarkRequest extends Watermark<WatermarkRequest> {

    @SerializedName("files")
    private Set<SourceImage> sourceImages = newHashSet();

    public WatermarkRequest setSourceImages(Set<SourceImage> sourceImages) {
        this.sourceImages = sourceImages;
        return this;
    }

    public WatermarkRequest addSourceImages(SourceImage sourceImage) {
        this.sourceImages.add(sourceImage);
        return this;
    }
}
