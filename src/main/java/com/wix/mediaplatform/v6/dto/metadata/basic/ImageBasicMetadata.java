package com.wix.mediaplatform.v6.dto.metadata.basic;

public class ImageBasicMetadata extends BasicMetadata {

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

    @Override
    public String toString() {
        return "ImageBasicMetadata{" +
                "height=" + height +
                ", width=" + width +
                ", colorspace='" + colorspace + '\'' +
                ", format='" + format + '\'' +
                '}';
    }
}
