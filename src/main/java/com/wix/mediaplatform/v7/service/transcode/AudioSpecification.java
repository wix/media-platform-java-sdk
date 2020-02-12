package com.wix.mediaplatform.v7.service.transcode;

public class AudioSpecification {

    private String channels;

    private AudioCodec codec;

    public AudioSpecification() {
    }

    public String getChannels() {
        return channels;
    }

    public AudioCodec getCodec() {
        return codec;
    }

    @Override
    public String toString() {
        return "AudioSpecification{" +
                "channels='" + channels + '\'' +
                ", codec=" + codec +
                '}';
    }
}
