package com.wix.mediaplatform.v8.service.transcode;

public class AudioCodec {

    private Float cbr;

    private String name;

    public Float getCbr() {
        return cbr;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "AudioCodec{" +
                "cbr=" + cbr +
                ", name='" + name + '\'' +
                '}';
    }
}
