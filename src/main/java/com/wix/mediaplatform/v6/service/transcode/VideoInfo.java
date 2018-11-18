package com.wix.mediaplatform.v6.service.transcode;

public class VideoInfo {

    private String format;

    private Float videoBitrate;

    private Float audioBitrate;

    private String quality;

    private Integer width;

    private Integer height;

    private String tag;

    private String fps;

    private Float duration;

    private String type;

    private String displayAspectRatio;

    public VideoInfo() {
    }

    public String getFormat() {
        return format;
    }

    public Float getVideoBitrate() {
        return videoBitrate;
    }

    public Float getAudioBitrate() {
        return audioBitrate;
    }

    public String getQuality() {
        return quality;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public String getTag() {
        return tag;
    }

    public String getFps() {
        return fps;
    }

    public Float getDuration() {
        return duration;
    }

    public String getType() {
        return type;
    }

    public String getDisplayAspectRatio() {
        return displayAspectRatio;
    }
}
