package com.wix.mediaplatform.dto.live;

public class ParamsOption {
    private Integer bitrate;
    private Integer height;
    private Integer width;

    public ParamsOption() {
    }

    public Integer getBitrate() {
        return bitrate;
    }

    public ParamsOption setBitrate(Integer bitrate) {
        this.bitrate = bitrate;
        return this;
    }

    public Integer getHeight() {
        return height;
    }

    public ParamsOption setHeight(Integer height) {
        this.height = height;
        return this;
    }

    public Integer getWidth() {
        return width;
    }

    public ParamsOption setWidth(Integer width) {
        this.width = width;
        return this;
    }
}