package com.wix.mediaplatform.v8.metadata.basic;

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

    public ImageBasicMetadata setHeight(Integer height) {
        this.height = height;
        return this;
    }

    public Integer getWidth() {
        return width;
    }

    public ImageBasicMetadata setWidth(Integer width) {
        this.width = width;
        return this;
    }

    public String getColorspace() {
        return colorspace;
    }

    public ImageBasicMetadata setColorspace(String colorspace) {
        this.colorspace = colorspace;
        return this;
    }

    public String getFormat() {
        return format;
    }

    public ImageBasicMetadata setFormat(String format) {
        this.format = format;
        return this;
    }
}
