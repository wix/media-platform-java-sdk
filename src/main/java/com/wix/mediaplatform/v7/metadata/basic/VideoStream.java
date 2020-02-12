package com.wix.mediaplatform.v7.metadata.basic;

public class VideoStream {

    private String codecLongName;

    private String codecTag;

    private String codecName;

    private Integer height;

    private Integer width;

    private Long duration;

    private Float bitrate;

    private Integer index;

    private String rFrameRate;

    private String avgFrameRate;

    private String smapleAspectRatio;

    private String displayAspectRatio;

    public VideoStream() {
    }

    public String getCodecLongName() {
        return codecLongName;
    }

    public String getCodecTag() {
        return codecTag;
    }

    public String getCodecName() {
        return codecName;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }

    public Long getDuration() {
        return duration;
    }

    public Float getBitrate() {
        return bitrate;
    }

    public Integer getIndex() {
        return index;
    }

    public String getrFrameRate() {
        return rFrameRate;
    }

    public String getAvgFrameRate() {
        return avgFrameRate;
    }

    public String getSmapleAspectRatio() {
        return smapleAspectRatio;
    }

    public String getDisplayAspectRatio() {
        return displayAspectRatio;
    }

    @Override
    public String toString() {
        return "VideoStream{" +
                "codecLongName='" + codecLongName + '\'' +
                ", codecTag='" + codecTag + '\'' +
                ", codecName='" + codecName + '\'' +
                ", height=" + height +
                ", width=" + width +
                ", duration=" + duration +
                ", bitrate=" + bitrate +
                ", index=" + index +
                ", rFrameRate='" + rFrameRate + '\'' +
                ", avgFrameRate='" + avgFrameRate + '\'' +
                ", smapleAspectRatio='" + smapleAspectRatio + '\'' +
                ", displayAspectRatio='" + displayAspectRatio + '\'' +
                '}';
    }
}
