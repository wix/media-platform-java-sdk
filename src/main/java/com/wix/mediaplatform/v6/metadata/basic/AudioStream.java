package com.wix.mediaplatform.v6.metadata.basic;


public class AudioStream {

    private String codecLongName;

    private String codecTag;

    private String codecName;

    private Long duration;

    private Float bitrate;

    private Integer index;

    public AudioStream() {
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

    public Long getDuration() {
        return duration;
    }

    public Float getBitrate() {
        return bitrate;
    }

    public Integer getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "AudioStream{" +
                "codecLongName='" + codecLongName + '\'' +
                ", codecTag='" + codecTag + '\'' +
                ", codecName='" + codecName + '\'' +
                ", duration=" + duration +
                ", bitrate=" + bitrate +
                ", index=" + index +
                '}';
    }
}
