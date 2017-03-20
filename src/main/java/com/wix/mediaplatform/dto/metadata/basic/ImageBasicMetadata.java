package com.wix.mediaplatform.dto.metadata.basic;

public class ImageBasicMetadata implements BasicMetadata {

    private Integer height;

    private Integer width;

    private String colorspace;

    private String format;

    public ImageBasicMetadata() {
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }

    public String getColorspace() {
        return colorspace;
    }

    public String getFormat() {
        return format;
    }
}
