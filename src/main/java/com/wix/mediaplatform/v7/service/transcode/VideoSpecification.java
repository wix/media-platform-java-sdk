package com.wix.mediaplatform.v7.service.transcode;

public class VideoSpecification {

    private String frameRate;

    private VideoCodec codec;

    private Resolution resolution;

    private Float keyFrame;

    public String getFrameRate() {
        return frameRate;
    }

    public VideoCodec getCodec() {
        return codec;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public Float getKeyFrame() {
        return keyFrame;
    }

    @Override
    public String toString() {
        return "VideoSpecification{" +
                "frameRate='" + frameRate + '\'' +
                ", codec=" + codec +
                ", resolution=" + resolution +
                ", keyFrame=" + keyFrame +
                '}';
    }
}


/**
 "filter": "scale=256:144,setsar=1/1",
 "frameRate": "29.97",
 "codec": {
 "profile": "main",
 "maxRate": 250000,
 "crf": 20,
 "name": "h.264",
 "level": "3.1"
 },
 "resolution": {
 "width": 256,
 "height": 144
 },
 "keyFrame": 60
 }
 */
