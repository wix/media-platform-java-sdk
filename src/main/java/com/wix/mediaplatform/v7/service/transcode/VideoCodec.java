package com.wix.mediaplatform.v7.service.transcode;

public class VideoCodec {

    private String profile;

    private Float maxRate;

    private Float crf;

    private String name;

    private String level;

    public VideoCodec() {
    }

    public String getProfile() {
        return profile;
    }

    public Float getMaxRate() {
        return maxRate;
    }

    public Float getCrf() {
        return crf;
    }

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "VideoCodec{" +
                "profile='" + profile + '\'' +
                ", maxRate=" + maxRate +
                ", crf=" + crf +
                ", name='" + name + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}

/**
 * {
 "profile": "main",
 "maxRate": 250000,
 "crf": 20,
 "name": "h.264",
 "level": "3.1"
 }
 */
